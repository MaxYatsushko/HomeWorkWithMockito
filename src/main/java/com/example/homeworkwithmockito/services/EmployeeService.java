package com.example.homeworkwithmockito.services;

import com.example.homeworkwithmockito.entity.Employee;
import com.example.homeworkwithmockito.excetions.EmployeeAlreadyAddedException;
import com.example.homeworkwithmockito.excetions.EmployeeNotFoundException;
import com.example.homeworkwithmockito.excetions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final int MAX_EMPLOYEES_COUNT = 2;

    private final List<Employee> employees = new ArrayList<>();

    public Employee add(String firstName, String lastName) {

        if (employees.size() == MAX_EMPLOYEES_COUNT) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }

        Employee employee = new Employee(firstName, lastName);

        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("В массиве уже есть такой сотрудник");
        }

        employees.add(employee);

        return employee;
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = null;

        for (Employee e : employees) {
            if (e != null && firstName.equals(e.getFirstName()) && lastName.equals(e.getLastName())) {
                employee = e;
            }
        }

        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }

        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        Employee employee = find(firstName, lastName);

        for (Employee e : employees) {
            if (e.equals(employee)) {
                return e;
            }
        }

        return employee;
    }

    public List<Employee> getAll() {
        return employees;
    }
}
