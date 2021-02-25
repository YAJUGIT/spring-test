package com.employee.service;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class EmployeeServiceImplTest {
    @Autowired
    EmployeeService employeeService;

    @MockBean
    EmployeeRepo employeeRepo;

    @Test
    void createEmployee() {
    }

    @Test
    @DisplayName("Test findById Success")
    void getEmployee() {
        // Setup your mock repository
        Employee employee = new Employee(1, "Stephen", "Colbert", "sbc.amail.com", "234-345-567");
        doReturn(Optional.of(employee)).when(employeeRepo.findById(1));

        // Execute the service call
        Optional<Employee> responseEmployee = employeeService.getEmployee(1);

        // Assert the response
        Assertions.assertTrue(responseEmployee.isPresent(), "Employee was not found");
        Assertions.assertSame(responseEmployee.get(), employee, "The Employee returned was not the same as the mock");

    }

    @Test
    @DisplayName("Test findById Not Found")
    void testFindByIdNotFound() {
        // Setup our mock repository
        doReturn(Optional.empty()).when(employeeRepo).findById(1);

        // Execute the service call
        Optional<Employee> returnedEmployee = employeeService.getEmployee(1);

        // Assert the response
        Assertions.assertFalse(returnedEmployee.isPresent(), "Employee should not be found");
    }

    @Test
    @DisplayName("Test findAll")
    void testFindAll() {
        // Setup our mock repository
        // Setup your mock repository
        Employee employee1 = new Employee(1, "Stephen", "Colbert", "sbc.amail.com", "234-345-567");
        Employee employee2 = new Employee(2, "Rama", "Chandra", "sbc.amail.com", "234-345-567");
        doReturn(Arrays.asList(employee1, employee2)).when(employeeRepo).findAll();

        // Execute the service call
        List<Employee> employees = employeeService.findAll();

        // Assert the response
        Assertions.assertEquals(2, employees.size(), "findAll should return 2 widgets");
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void deleteEmployee() {
    }
}