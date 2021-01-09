package com.employee.controller;

import com.employee.exception.EmployeeException;
import com.employee.exception.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

public interface BaseController {
    public static final Logger LOG = LoggerFactory.getLogger(com.employee.controller.BaseController.class);

    @ExceptionHandler(EmployeeException.class)
    @ResponseBody
    public default String handleException(EmployeeException ex, HttpServletResponse response) throws JsonProcessingException {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setErrorCode(ex.getErrorCode());
        errorResponse.setErrorMessage(ex.getErrorMessage());
        errorResponse.setStatusCode(ex.getStatusCode());

        LOG.debug("ErrorCode : {} and errormessage :{}" + errorResponse.getErrorCode() , ex.getMessage(), errorResponse.getErrorMessage());
        ObjectMapper mapper = new ObjectMapper();
        return  mapper.writeValueAsString(errorResponse);
    }
}
