package com.example.homeworkwithmockito.excetions;

public class DepartmentSearchException extends RuntimeException{
    private final int errorCode;

    public DepartmentSearchException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    public int getErrorCode() {
        return errorCode;
    }
}
