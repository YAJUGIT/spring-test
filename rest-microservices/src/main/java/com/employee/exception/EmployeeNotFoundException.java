package com.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends EmployeeException{
    public EmployeeNotFoundException(EmployeeError error, String message) {
        super(error, message);
    }

    public static RuntimeException notFound(long employeeId){
        return new EmployeeNotFoundException(EmployeeError.EMPLOYEE_NOT_FOUND,
                String.format("Employee not found for the employee ID: %d", employeeId));
    }
}
