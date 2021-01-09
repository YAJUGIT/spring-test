package com.employee.controller.impl;

import com.employee.controller.EmployeeController;
import com.employee.dto.EmployeeDTO;
import com.employee.entity.Employee;
import com.employee.service.EmployeeService;
import com.employee.util.EmployeeConstants;
import com.employee.util.EmployeeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class EmployeeControllerImpl implements EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Override
    public ResponseEntity<String> createEmployee(EmployeeDTO employeeDTO) {
        employeeService.createEmployee(EmployeeUtil.getEmployee(employeeDTO));
        return new ResponseEntity<>(EmployeeConstants.EMPLOYEE_CREATION_SUCCESS, HttpStatus.CREATED);
    }

    @Override
    public Optional<Employee> getEmployee(int id) {
        return employeeService.getEmployee(id);
    }

    @Override
    public ResponseEntity<String> updateEmployee(EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(EmployeeUtil.getEmployee(employeeDTO));
        return new ResponseEntity<>(EmployeeConstants.EMPLOYEE_UPDATION_SUCCESS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteEmployee(int id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(EmployeeConstants.EMPLOYEE_DELETION_SUCCESS, HttpStatus.OK);
    }
}
