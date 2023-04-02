package com.example.homeworkwithmockito.services;

import com.example.homeworkwithmockito.entity.Employee;
import com.example.homeworkwithmockito.excetions.DepartmentSearchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    @Autowired
    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmployeeWithMinSalary(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment().getId() == departmentId)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new DepartmentSearchException("Департамент не найден", 555));
    }

    @Override
    public Employee getEmployeeWithMaxSalary(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment().getId() == departmentId)
                .max(Comparator.comparing(e -> e.getSalary()))
                .orElseThrow(() -> new DepartmentSearchException("Департамент не найден", 555));
    }

    @Override
    public Map<String, List<Employee>> getAll(Integer departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> departmentId == null || employee.getDepartment().getId() == departmentId)
                .collect(Collectors.groupingBy(
                        employee -> employee.getDepartment().getName(),
                        Collectors.mapping(e -> e, Collectors.toList()))
                );
    }

    @Override
    public String getSumSalary(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment().getId() == departmentId)
                .collect(Collectors.summingDouble(Employee :: getSalary)).toString();
    }
    @Override
    public String getMinSalary(int departmentId) {
        Employee employee = employeeService.getAll().stream()
                .filter(emp -> emp.getDepartment().getId() == departmentId)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new DepartmentSearchException("Департамент не найден", 555));

        return String.valueOf(employee.getSalary());
    }
    @Override
    public String getMaxSalary(int departmentId) {
        Employee employee = employeeService.getAll().stream()
                .filter(emp -> emp.getDepartment().getId() == departmentId)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new DepartmentSearchException("Департамент не найден", 555));

        return String.valueOf(employee.getSalary());
    }
}