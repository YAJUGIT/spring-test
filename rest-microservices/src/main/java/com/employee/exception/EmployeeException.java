package com.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class EmployeeException extends RuntimeException {
    private final EmployeeError error;
    private final String messageAppender;

    public EmployeeException(EmployeeError error, Throwable th){
        super(th);
        this.error = error;
        this.messageAppender = null;
    }

    public EmployeeException(EmployeeError error){
        this.error = error;
        this.messageAppender = null;
    }

    public EmployeeException(EmployeeError error, String messageAppender){
        this.error = error;
        this.messageAppender = messageAppender;
    }

    @Override
    public String getMessage() {
        String message;
        switch (error.getCode()) {
            case 10001:
                message = error.getMessage();
                break;
            case 10002:
            case 10003:
            case 10004:
                message = error.getMessage();
                if (!StringUtils.isEmpty(messageAppender)) {
                    message = error.getMessage() + messageAppender;
                }
                break;
            default:
                message = "Internal server error";

        }
     return message;
    }

    public int getStatusCode(){
        int code;
        switch(error.getCode()){
            case 10001:
            case 10002:
            case 10003:
                code = HttpStatus.BAD_REQUEST.value();
                break;
            default:
                code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
        return code;
    }

    public String getErrorMessage(){
        if(!StringUtils.isEmpty(messageAppender)){
            return error.getMessage() + messageAppender;
        }
        return error.getMessage();
    }

    public int getErrorCode(){
        return error.getCode();
    }
}
