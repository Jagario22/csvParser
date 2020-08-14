package com.nix.reflection.csvParser.data;

import com.nix.reflection.csvParser.annotation.PropertyKey;

public class Employee {

    @PropertyKey("name")
    public String name;

    @PropertyKey("age")
    private int age;

    @PropertyKey("gender")
    public String gender;

    @PropertyKey("occupation")
    private String occupation;

    public boolean testField = true;

    public Employee() {
    }

    public Employee(String name, int age, String gender, String occupation, boolean testField) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
        this.testField = testField;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", occupation='" + occupation + '\'' +
                ", testField=" + testField +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public boolean isTestField() {
        return testField;
    }
}
