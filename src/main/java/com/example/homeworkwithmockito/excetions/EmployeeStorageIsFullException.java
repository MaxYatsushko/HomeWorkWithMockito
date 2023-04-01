package com.example.homeworkwithmockito.excetions;

public class EmployeeStorageIsFullException extends EmployeeException {
    public EmployeeStorageIsFullException(String message, int errorCode) {
        super(message, errorCode);
    }
}
