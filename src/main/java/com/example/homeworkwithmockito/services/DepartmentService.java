package com.example.homeworkwithmockito.services;

import com.example.homeworkwithmockito.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getEmployeeWithMinSalary(int departmentId);
    Employee getEmployeeWithMaxSalary(int departmentId);
    Map<String, List<Employee>> getAll(Integer departmentId);
    String getSumSalary(int departmentId);
    String getMinSalary(int departmentId);
    String getMaxSalary(int departmentId);
}
