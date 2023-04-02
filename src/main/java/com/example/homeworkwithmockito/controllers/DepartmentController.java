package com.example.homeworkwithmockito.controllers;

import com.example.homeworkwithmockito.entity.Employee;
import com.example.homeworkwithmockito.excetions.DepartmentSearchException;
import com.example.homeworkwithmockito.services.DepartmentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentServiceImpl departmentServiceImpl;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentServiceImpl = departmentService;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DepartmentSearchException.class)
    public String handleException(DepartmentSearchException e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @GetMapping(path = "/max-salary")
    public Employee maxSalary(@RequestParam Integer departmentId) {
        return departmentServiceImpl.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping(path = "/min-salary")
    public Employee minSalary(@RequestParam Integer departmentId) {
        return departmentServiceImpl.getEmployeeWithMinSalary(departmentId);
    }

    @GetMapping(path = "/{id}/employees")
    public Map<String, List<Employee>> allByDepartmentId(@PathVariable("id") Integer departmentId) {
        return departmentServiceImpl.getAll(departmentId);
    }

    @GetMapping(path = "/employees")
    public Map<String, List<Employee>> all() {
        return departmentServiceImpl.getAll(null);
    }

    @GetMapping(path = "/{id}/salary/sum")
    public String getSumSalary(@PathVariable("id") Integer departmentId) {
        return departmentServiceImpl.getSumSalary(departmentId);
    }
    @GetMapping(path = "/{id}/salary/min")
    public String getMaxSalary(@PathVariable("id") Integer departmentId) {
        return departmentServiceImpl.getMinSalary(departmentId);
    }

    @GetMapping(path = "/{id}/salary/max")
    public String getMinSalary(@PathVariable("id") Integer departmentId) {
        return departmentServiceImpl.getMaxSalary(departmentId);
    }
}