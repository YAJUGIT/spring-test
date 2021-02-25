package com.employee.service;

import com.employee.entity.Employee;
import com.employee.exception.EmployeeException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    void createEmployee(Employee employee) throws EmployeeException;
    Optional<Employee> getEmployee(int id) throws EmployeeException;
    void updateEmployee(Employee employee) throws EmployeeException;
    void deleteEmployee(int id) throws EmployeeException;
    List<Employee> findAll();
}
