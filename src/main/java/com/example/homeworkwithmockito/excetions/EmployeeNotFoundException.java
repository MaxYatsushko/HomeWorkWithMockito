package com.example.homeworkwithmockito.excetions;

public class EmployeeNotFoundException extends EmployeeException {
    public EmployeeNotFoundException(String message, int errorCode) {
        super(message, errorCode);
    }
}
