package com.employee.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Slf4j
@ControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler(EmployeeException.class)
    ResponseEntity<String> handleEmployeeError(EmployeeException employeeException) {
        log.debug("ErrorCode :{}, ErrorMessage :{}", employeeException.getErrorCode(), employeeException.getMessage());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Application-Error-Code", String.valueOf(employeeException.getErrorCode()));
        return ResponseEntity.status(getHttpStatus(employeeException)).headers(httpHeaders).body(employeeException.getMessage());
    }

    private HttpStatus getHttpStatus(EmployeeException exception) {
        return Optional.ofNullable(AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class))
                .map(ResponseStatus::value)
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
