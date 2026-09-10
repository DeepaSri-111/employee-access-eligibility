package com.example.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        AccessEligibilityService service =
                new AccessEligibilityService();

        List<Employee> employees = new ArrayList<>();

        System.out.print("Enter number of employees: ");
        int numberOfEmployees = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= numberOfEmployees; i++) {

            System.out.println("\nEnter details for Employee " + i);

            System.out.print("Employee ID: ");
            String employeeId = scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Department: ");
            String department = scanner.nextLine();

            System.out.print("Employment Type (Active/Inactive): ");
            String employmentType = scanner.nextLine();

            System.out.print("Security Clearance Level (1-5): ");
            int securityClearanceLevel = scanner.nextInt();

            System.out.print("Is Employee ID Valid? (true/false): ");
            boolean idValid = scanner.nextBoolean();

            System.out.print("Requested Access Level (1-5): ");
            int requestedAccessLevel = scanner.nextInt();
            scanner.nextLine();

            try {

                Employee employee = new Employee(
                        employeeId,
                        name,
                        age,
                        department,
                        employmentType,
                        securityClearanceLevel,
                        idValid
                );

                String result =
                        service.checkEligibility(
                                employee,
                                requestedAccessLevel
                        );

                employees.add(employee);

                System.out.println("\nResult for " + name + ":");
                System.out.println(result);

            } catch (InvalidEmployeeException e) {

                System.out.println(
                        "Invalid input: " + e.getMessage()
                );
            }
        }

        scanner.close();

        System.out.println("\nEmployee processing completed.");
    }
}
