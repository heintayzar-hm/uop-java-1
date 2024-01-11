package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AddColumns {
    private static String relativePath = "src/main/java/com/example/";
    private static String csvFile = relativePath + "OldEmployeeData.csv";
    private static String outputFile = relativePath + "NewEmployeeData.csv";
    private static String[] newColumns = { "Department", "Salary", "Name" };
    private static String[] departments = { "IT", "Finance", "HR", "Marketing", "Operations" };
    private static int minSalary = 50000;
    private static int maxSalary = 100000;
    private static String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static void main(String[] args) {

        List<String> lines = new ArrayList<>();

        // Read the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add new columns headers
        lines.set(0, lines.get(0) + "," + String.join(",", newColumns));

        // Add random data to new columns
        for (int i = 1; i < lines.size(); i++) {
            String randomDepartment = generateRandomDepartment();
            String randomSalary = generateRandomSalary();
            String randomName = generateRandomName();
            lines.set(i, lines.get(i) + "," + randomDepartment + "," + randomSalary + "," + randomName);
        }

        // Write the modified data to a new CSV file
        try (FileWriter writer = new FileWriter(outputFile)) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Random columns added successfully.");
    }

    private static String generateRandomDepartment() {
        Random random = new Random();
        int index = random.nextInt(departments.length);
        return departments[index];
    }

    private static String generateRandomSalary() {
        Random random = new Random();
        int randomSalary = random.nextInt(maxSalary - minSalary + 1) + minSalary;
        return String.valueOf(randomSalary);
    }

    private static String generateRandomName() {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        int length = random.nextInt(10) + 5;
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(lexicon.length());
            builder.append(lexicon.charAt(index));
        }
        return builder.toString();
    }
}
