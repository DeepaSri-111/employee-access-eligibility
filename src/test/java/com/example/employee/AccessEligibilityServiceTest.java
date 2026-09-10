package com.example.employee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AccessEligibilityServiceTest {

    private final AccessEligibilityService service =
            new AccessEligibilityService();

    @Test
    public void testEligibleEmployee() throws InvalidEmployeeException {

        Employee employee = new Employee(
                "EMP001",
                "Arun",
                30,
                "IT",
                "Active",
                5,
                true
        );

        String result =
                service.checkEligibility(employee, 3);

        assertEquals("Eligible", result);
    }

    @Test
    public void testMinimumAgeBoundary()
            throws InvalidEmployeeException {

        Employee employee = new Employee(
                "EMP002",
                "Priya",
                21,
                "HR",
                "Active",
                3,
                true
        );

        String result =
                service.checkEligibility(employee, 3);

        assertEquals("Eligible", result);
    }

    @Test
    public void testConditionallyEligibleEmployee()
            throws InvalidEmployeeException {

        Employee employee = new Employee(
                "EMP003",
                "Rahul",
                25,
                "Finance",
                "Active",
                2,
                true
        );

        String result =
                service.checkEligibility(employee, 4);

        assertTrue(result.startsWith("Conditionally Eligible"));
    }

    @Test
    public void testNegativeAge() {

        Employee employee = new Employee(
                "EMP004",
                "Kiran",
                -5,
                "IT",
                "Active",
                3,
                true
        );

        assertThrows(
                InvalidEmployeeException.class,
                () -> service.checkEligibility(employee, 2)
        );
    }

    @Test
    public void testInvalidAccessLevel() {

        Employee employee = new Employee(
                "EMP005",
                "Meena",
                25,
                "IT",
                "Active",
                5,
                true
        );

        assertThrows(
                InvalidEmployeeException.class,
                () -> service.checkEligibility(employee, 6)
        );
    }

    @Test
    public void testUnderageEmployee()
            throws InvalidEmployeeException {

        Employee employee = new Employee(
                "EMP006",
                "Ravi",
                20,
                "IT",
                "Active",
                5,
                true
        );

        String result =
                service.checkEligibility(employee, 2);

        assertTrue(result.contains("at least 21"));
        assertTrue(result.startsWith("Not Eligible"));
    }

    @Test
    public void testUnauthorizedDepartment()
            throws InvalidEmployeeException {

        Employee employee = new Employee(
                "EMP007",
                "Suresh",
                30,
                "Marketing",
                "Active",
                5,
                true
        );

        String result =
                service.checkEligibility(employee, 2);

        assertTrue(result.contains("department is not authorized"));
    }

    @Test
    public void testInactiveEmployee()
            throws InvalidEmployeeException {

        Employee employee = new Employee(
                "EMP008",
                "Divya",
                28,
                "IT",
                "Inactive",
                5,
                true
        );

        String result =
                service.checkEligibility(employee, 2);

        assertTrue(result.contains("active employment"));
    }

    @Test
    public void testInvalidEmployeeId()
            throws InvalidEmployeeException {

        Employee employee = new Employee(
                "EMP009",
                "Vijay",
                28,
                "Finance",
                "Active",
                5,
                false
        );

        String result =
                service.checkEligibility(employee, 2);

        assertTrue(result.contains("Employee ID is invalid"));
    }

    @Test
    public void testMultipleFailures()
            throws InvalidEmployeeException {

        Employee employee = new Employee(
                "EMP010",
                "Anitha",
                19,
                "Marketing",
                "Inactive",
                1,
                false
        );

        String result =
                service.checkEligibility(employee, 4);

        assertTrue(result.startsWith("Not Eligible"));
        assertTrue(result.contains("at least 21"));
        assertTrue(result.contains("department is not authorized"));
        assertTrue(result.contains("active employment"));
        assertTrue(result.contains("Employee ID is invalid"));
        assertTrue(result.contains("Security clearance"));
    }
}