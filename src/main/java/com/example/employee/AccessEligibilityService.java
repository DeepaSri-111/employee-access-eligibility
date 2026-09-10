package com.example.employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccessEligibilityService {

    private static final List<String> AUTHORIZED_DEPARTMENTS =
            Arrays.asList("IT", "HR", "Finance", "Administration");

    public String checkEligibility(Employee employee, int requestedAccessLevel)
            throws InvalidEmployeeException {

        if (employee == null) {
            throw new InvalidEmployeeException("Employee details cannot be null.");
        }

        if (employee.getName() == null || employee.getName().trim().isEmpty()) {
            throw new InvalidEmployeeException("Employee name cannot be empty.");
        }

        if (employee.getEmployeeId() == null ||
                employee.getEmployeeId().trim().isEmpty()) {
            throw new InvalidEmployeeException("Employee ID cannot be empty.");
        }

        if (employee.getAge() < 0) {
            throw new InvalidEmployeeException("Age cannot be negative.");
        }

        if (requestedAccessLevel < 1 || requestedAccessLevel > 5) {
            throw new InvalidEmployeeException(
                    "Requested access level must be between 1 and 5.");
        }

        List<String> reasons = new ArrayList<>();

        if (employee.getAge() < 21) {
            reasons.add("Employee must be at least 21 years old.");
        }

        if (!isAuthorizedDepartment(employee.getDepartment())) {
            reasons.add("Employee department is not authorized.");
        }

        if (!"Active".equalsIgnoreCase(employee.getEmploymentType())) {
            reasons.add("Employee does not have active employment status.");
        }

        if (!employee.isIdValid()) {
            reasons.add("Employee ID is invalid.");
        }

        if (employee.getSecurityClearanceLevel() < requestedAccessLevel) {
            reasons.add("Security clearance level is insufficient for requested access.");
        }

        if (reasons.isEmpty()) {
            return "Eligible";
        }

        boolean basicRequirementsPassed =
                employee.getAge() >= 21
                && isAuthorizedDepartment(employee.getDepartment())
                && "Active".equalsIgnoreCase(employee.getEmploymentType())
                && employee.isIdValid();

        if (basicRequirementsPassed
                && employee.getSecurityClearanceLevel() < requestedAccessLevel) {

            return "Conditionally Eligible - "
                    + String.join(" ", reasons);
        }

        return "Not Eligible - Reasons: "
                + String.join(" ", reasons);
    }

    private boolean isAuthorizedDepartment(String department) {

        if (department == null) {
            return false;
        }

        for (String authorizedDepartment : AUTHORIZED_DEPARTMENTS) {

            if (authorizedDepartment.equalsIgnoreCase(department.trim())) {
                return true;
            }
        }

        return false;
    }
}
