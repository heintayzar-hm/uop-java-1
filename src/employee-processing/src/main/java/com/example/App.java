package com.example;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;


// csv reader and return as list of Employee objects

public class App {
    private static final String relativePath = "src/main/java/com/example/";
    public static Function<Employee, String> getEmployeeNameAndDepartment = (Employee employee) -> {
        return employee.getDepartment() + " " + employee.getName();
    };

    public static void main(String[] args) throws IOException {
        String csvFile = relativePath + "NewEmployeeData.csv";
        // age threshold
        int ageThreshold = 30;
        List<Employee> employees = EmployeeReader.read(csvFile);


        List<String> employeeNamesAndDepartments = employees.stream()
                .map(getEmployeeNameAndDepartment)
                .toList();
        System.out.println("Employee names and departments(first 2): " +
                employeeNamesAndDepartments.subList(0, 2));

        double averageSalary = getAverageSalary(employees);

        List<Employee> filteredEmployees = filterEmployees(employees, ageThreshold);


         System.out.println("Average salary: " + averageSalary);
         System.out.println("Filtered employees size: " + filteredEmployees.size());


        // try to populate a list of employees with 1000000 entries
        List<Employee> moreEmployees = new ArrayList<>(employees);
        // more
       for (int i = 0; i < 20000; i++) {
           moreEmployees.addAll(employees);
       }

    System.out.println("Number of employees: " + moreEmployees.size());
        // additional code to measure time taken for each operation
        System.out.println("Time taken for sequential operations:");
        measureTime(() -> getAverageSalary(moreEmployees)); // sequential
        measureTime(() -> filterEmployees(moreEmployees, ageThreshold)); // sequential

        System.out.println("Time taken for parallel operations:");
        measureTime(() -> getAverageSalaryInParallel(moreEmployees)); // parallel
        measureTime(() -> filterEmployeesInParallel(moreEmployees, ageThreshold)); // parallel

        // additional code to measure time taken for concurrent tasks
        System.out.println("Time taken for concurrent operations:");
        measureParallelTasks(() -> getAverageSalary(moreEmployees));
        measureParallelTasks(() -> filterEmployees(moreEmployees, ageThreshold));

    }

    public static double getAverageSalary(List<Employee> employees) {
        // time complexity: O(n)
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }

    public static List<Employee> filterEmployees(List<Employee> employees, int ageThreshold) {
        // time complexity: O(n)
        return employees.stream()
                .filter(employee -> employee.getAge() > ageThreshold)
                .collect(Collectors.toList());
    }

    /////////////////////////// OPTIMIZATION attempts ///////////////////////////

    /////////////// Parallel Stream ////////////////////////

    // trying to optimize by using parallel stream
    public static double getAverageSalaryInParallel(List<Employee> employees) {
        // time complexity: O(n)
        return employees.parallelStream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }

    public static List<Employee> filterEmployeesInParallel(List<Employee> employees, int ageThreshold) {
        // time complexity: O(n)
        // parallel stream and make it perform better
        return employees.parallelStream()
                .filter(employee -> employee.getAge() > ageThreshold)
                .collect(Collectors.toList());
    }

    /////////////// Concurrent library ////////////////////////

    public static <T> T measureParallelTasks(Callable<T> task) {
        long startTime = System.currentTimeMillis();

        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        try {
            List<Future<T>> futures = new ArrayList<>();
            for (int i = 0; i < numThreads; i++) {
                Future<T> future = executorService.submit(task);
                futures.add(future);
            }

            T result = null;
            for (Future<T> future : futures) {
                try {
                    result = future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

            long endTime = System.currentTimeMillis();
            System.out.println("Time taken in current: " + (endTime - startTime) + " ms");
            return result;
        } finally {
            executorService.shutdown();
        }
    }

    // time taken based on operation
    public static <T> void measureTime(MeasureTimeOperation<T> operation) {
        long startTime = System.currentTimeMillis();
        T result = operation.performOperation();
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        System.out.println("Time taken: " + timeTaken + " ms");
    }

    interface MeasureTimeOperation<T> {
        T performOperation();
    }
}
