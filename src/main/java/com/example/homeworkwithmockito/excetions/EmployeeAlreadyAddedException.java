package com.example.homeworkwithmockito.excetions;

public class EmployeeAlreadyAddedException extends RuntimeException {

    public EmployeeAlreadyAddedException(String message) {
        super(message);
    }
}