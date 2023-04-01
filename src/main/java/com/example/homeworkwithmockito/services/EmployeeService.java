package com.example.homeworkwithmockito.services;

import com.example.homeworkwithmockito.entity.Employee;

import java.util.List;


public interface EmployeeService {
    Employee add(String firstName, String lastName, float salary, int departmentId);
    Employee find(String firstName, String lastName);
    Employee remove(String firstName, String lastName);
    List<Employee> getAll();
    void init();
}
