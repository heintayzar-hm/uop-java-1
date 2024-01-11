package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class EmployeeReader {
    public static List<Employee> read(String csvFile) {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            // Read the header
            String header = br.readLine();
            String[] headers = header.split(",");

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Employee employee = parseEmployee(data, headers);
                employees.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return employees;
    }

    private static Employee parseEmployee(String[] data, String[] headers) {
        return new Employee(
                data[0], Integer.parseInt(data[1]), data[2], Integer.parseInt(data[3]),
                Integer.parseInt(data[4]), data[5], data[6], Integer.parseInt(data[7]),
                data[8], data[9], data[10], data[11]);
    }
}

