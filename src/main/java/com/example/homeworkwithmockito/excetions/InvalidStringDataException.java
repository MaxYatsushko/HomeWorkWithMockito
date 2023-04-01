package com.example.homeworkwithmockito.excetions;

public class InvalidStringDataException extends EmployeeException {
    public InvalidStringDataException(String message, int errorCode) {
        super(message, errorCode);
    }
}
