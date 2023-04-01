package com.example.homeworkwithmockito.excetions;

public class EmployeeStorageIsFullException extends RuntimeException {

    public EmployeeStorageIsFullException(String message) {
        super(message);
    }
}
