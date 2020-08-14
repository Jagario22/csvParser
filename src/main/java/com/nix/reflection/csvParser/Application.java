package com.nix.reflection.csvParser;

import com.nix.reflection.csvParser.data.Employee;
import com.nix.reflection.csvParser.factory.PropertyHolderFactory;
import com.nix.reflection.csvParser.parser.csv.CsvTable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class Application {
    public static void main(String[] args) {
        try {
            Optional<CsvTable> csvTable = CsvTable.fromFile(Path.of("employers.csv"));

            List<Employee> employeeList = new PropertyHolderFactory<>(Employee.class).createByCsvTable(csvTable.get());
            employeeList.forEach(e -> System.out.println(e.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
