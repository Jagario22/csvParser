package com.nix.csvParser.parser.csv.factory;

import com.nix.reflection.csvParser.data.Employee;
import com.nix.reflection.csvParser.parser.csv.CsvTable;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PropertyHolderFactory {
    @Test
    void testCreatingByCsvTable() {
        Optional<CsvTable> csvTable = null;
        try {
            csvTable = CsvTable.fromFile(Path.of("employers.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Employee> employeesResult = new com.nix.reflection.csvParser.factory.PropertyHolderFactory<>(Employee.class).createByCsvTable(csvTable.get());

        List<Employee> employeesExpected = new ArrayList<>();
        employeesExpected.add(new Employee("Mike", 27, Employee.Gender.male, "janitor", true));
        employeesExpected.add(new Employee("Beth", 23, Employee.Gender.female, "recruiter", true));

        for (int i = 0; i < employeesResult.size(); i++) {
            assertEquals(employeesResult.get(i).getAge(), employeesExpected.get(i).getAge());
            assertEquals(employeesResult.get(i).getGender(), employeesExpected.get(i).getGender());
            assertEquals(employeesResult.get(i).getName(), employeesExpected.get(i).getName());
            assertEquals(employeesResult.get(i).getOccupation(), employeesExpected.get(i).getOccupation());
            assertEquals(employeesResult.get(i).isTestField(), employeesExpected.get(i).isTestField());
        }
    }
}
