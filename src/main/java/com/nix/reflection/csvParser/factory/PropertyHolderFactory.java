package com.nix.reflection.csvParser.factory;

import com.nix.reflection.csvParser.parser.csv.CsvTable;
import com.nix.reflection.csvParser.annotation.PropertyKey;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;

public class PropertyHolderFactory<T> {
    private Class<T> instanceClass;
    private T instance;

    public PropertyHolderFactory(Class<T> instanceClass) {
        this.instanceClass = instanceClass;
    }

    public List<T> createByCsvTable(CsvTable table) {
        List<T> list = new ArrayList<>();
        String value;

        for (int i = 0; i < table.height(); i++) {
            instance = createObj();
            for (Field field : instanceClass.getDeclaredFields()) {
                PropertyKey annotation = field.getAnnotation(PropertyKey.class);

                if (annotation == null)
                    continue;

                value = table.get(i, annotation.value());

                if (value == null)
                    continue;;

                setField(field, value);
            }
            list.add(instance);
        }
        return list;
    }


    private void setField(Field field, String value) {
        boolean fieldIsPrivate = false;

        if (field.getModifiers() == Modifier.PRIVATE) {
            field.setAccessible(true);
            fieldIsPrivate = true;
        }

        if (field.getModifiers() == Modifier.PUBLIC || fieldIsPrivate) {
            Class<?> fieldType = field.getType();
            try {
                if (fieldType == String.class) {
                    field.set(instance, value);
                }
                else if (fieldType == int.class || fieldType == Integer.class) {
                    field.set(instance, Integer.parseInt(value));
                }
                else {
                    throw new UnsupportedOperationException("Type " + fieldType + " is not supported");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    private T createObj() {
        T obj = null;
        try {
            obj = instanceClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
