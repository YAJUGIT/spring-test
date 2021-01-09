package com.employee.exception;

public enum EmployeeError {
    CUSTOM_JOB_NAME_IS_NULL(10001,"Job Menu Item is null "),
    USER_ID_IS_NULL(10002,"User Id is null "),
    JOB_LIST_EXISTS(10003,"Job Menu Item Exists , Please provide unique name "),
    USER_PREF_CREATION_FAILED(10004, "User Pref creation failed "),
    USER_PREF_UPDATION_FAILED(10005, "User Pref Update failed "),
    USER_PREF_DELETION_FAILED( 10006, "User Pref Deletion Failed");

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
