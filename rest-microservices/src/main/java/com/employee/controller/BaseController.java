package com.employee.controller;

import com.employee.exception.EmployeeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

public interface BaseController {
    public static final Logger LOG = LoggerFactory.getLogger(com.employee.controller.BaseController.class);

   @ExceptionHandler(EmployeeException.class)
    default ResponseEntity<String> handleEmployeeError(EmployeeException employeeException) {
        LOG.debug("ErrorCode :{}, ErrorMessage :{}", employeeException.getErrorCode(), employeeException.getMessage());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Application-Error-Code", String.valueOf(employeeException.getErrorCode()));
        return ResponseEntity.status(getHttpStatus(employeeException)).headers(httpHeaders).body(employeeException.getMessage());
    }

    default HttpStatus getHttpStatus(EmployeeException exception) {
        return Optional.ofNullable(AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class))
                .map(ResponseStatus::value)
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
