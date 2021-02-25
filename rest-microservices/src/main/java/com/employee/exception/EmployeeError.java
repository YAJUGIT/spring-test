package com.employee.exception;

public enum EmployeeError {
    CUSTOM_JOB_NAME_IS_NULL(10001,"Job Menu Item is null "),
    EMPLOYEE_ID_IS_NULL(10002,"Employee Id is null "),
    EMPLOYEE_NOT_FOUND(10003,"Employee not found"),
    EMPLOYEE_ID_IS_INVALID(10004,"Employee Id is Invalid");

    private int code;
    private String message;
    EmployeeError(int code, String message){
        this.code = code;
        this.message = message;
    }
    public int getCode(){return code;}

    public String getMessage(){
        return message;
    }

}
