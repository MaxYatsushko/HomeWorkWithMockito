package com.example.homeworkwithmockito.excetions;

public class EmployeeException extends RuntimeException {
    private final int errorCode;

    public EmployeeException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    public int getErrorCode() {
        return errorCode;
    }
}