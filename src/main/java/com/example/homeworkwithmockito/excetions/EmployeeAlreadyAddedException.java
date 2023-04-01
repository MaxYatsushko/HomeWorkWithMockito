package com.example.homeworkwithmockito.excetions;

public class EmployeeAlreadyAddedException extends EmployeeException {
    public EmployeeAlreadyAddedException(String message, int errorCode) {
        super(message, errorCode);
    }
}