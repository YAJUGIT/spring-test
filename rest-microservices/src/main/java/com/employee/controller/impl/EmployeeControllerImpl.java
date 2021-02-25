package com.employee.controller.impl;

import com.employee.controller.EmployeeController;
import com.employee.dto.EmployeeDTO;
import com.employee.exception.EmployeeError;
import com.employee.exception.EmployeeException;
import com.employee.service.EmployeeService;
import com.employee.util.EmployeeConstants;
import com.employee.util.EmployeeUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class EmployeeControllerImpl implements EmployeeController {

    EmployeeService employeeService;

    public EmployeeControllerImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public ResponseEntity<String> createEmployee(EmployeeDTO employeeDTO) {
        employeeService.createEmployee(EmployeeUtil.getEmployee(employeeDTO));
        return new ResponseEntity<>(EmployeeConstants.EMPLOYEE_CREATION_SUCCESS, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getEmployee(int id) {
        return employeeService.getEmployee(id)
                .map(employee -> {
                    try {
                        return ResponseEntity
                                .ok()
                                .eTag(Integer.toString(employee.getId()))
                                .location(new URI("/employee/" + employee.getId()))
                                .body(employee);
                    } catch (URISyntaxException e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    private void validateId(long id) {
        if (id < 0) throw new EmployeeException(EmployeeError.EMPLOYEE_ID_IS_INVALID);
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
