package com.example.homeworkwithmockito.services;

import com.example.homeworkwithmockito.entity.Employee;
import com.example.homeworkwithmockito.excetions.DepartmentSearchException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static com.example.homeworkwithmockito.entity.Department.DEPARTMENT_BY_ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {DepartmentServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class DepartmentServiceImplTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void getEmployeeWithMinSalary_success(){
        Integer departmentId = 1;
        float maxSalary = 35000;
        float minSalary = 30000;
        Employee employeeWithMaxSalary = new Employee("Ivan", "Ivanov", maxSalary, DEPARTMENT_BY_ID.get(departmentId));
        Employee employeeWithMinSalary = new Employee("Kiril", "Pletnev", minSalary, DEPARTMENT_BY_ID.get(departmentId));
        List<Employee> employees = List.of(employeeWithMaxSalary, employeeWithMinSalary);

        when(employeeService.getAll()).thenReturn(employees);

        Employee actualEmployee = departmentService.getEmployeeWithMinSalary(departmentId);

        assertEquals(employeeWithMinSalary, actualEmployee);
        assertTrue(minSalary < maxSalary);
    }

    @Test
    void getEmployeeWithMinSalary_withDepartmentSearch(){
        Integer departmentId = 1;

        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        String expectedError = "Департамент не найден";

        Exception exception = assertThrows(DepartmentSearchException.class, () -> departmentService.getEmployeeWithMinSalary(departmentId));
        assertEquals(expectedError, exception.getMessage());
    }

    @Test
    void getEmployeeWithMaxSalary_success(){
        Integer departmentId = 1;
        float maxSalary = 35000;
        float minSalary = 30000;
        Employee employeeWithMaxSalary = new Employee("Ivan", "Ivanov", maxSalary, DEPARTMENT_BY_ID.get(departmentId));
        Employee employeeWithMinSalary = new Employee("Kiril", "Pletnev", minSalary, DEPARTMENT_BY_ID.get(departmentId));
        List<Employee> employees = List.of(employeeWithMaxSalary, employeeWithMinSalary);

        when(employeeService.getAll()).thenReturn(employees);

        Employee actualEmployee = departmentService.getEmployeeWithMaxSalary(departmentId);

        assertEquals(employeeWithMaxSalary, actualEmployee);
        assertTrue(minSalary < maxSalary);
    }

    @Test
    void getEmployeeWithMaxSalary_withDepartmentSearch(){
        Integer departmentId = 1;

        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        String expectedError = "Департамент не найден";

        Exception exception = assertThrows(DepartmentSearchException.class, () -> departmentService.getEmployeeWithMaxSalary(departmentId));
        assertEquals(expectedError, exception.getMessage());
    }

    @Test
    void getAll_success(){
        Integer departmentId = 0;
        List<Employee> employees = new ArrayList<>();
        Employee workerBuh = new Employee("Инна", "Иванова", 70000f, DEPARTMENT_BY_ID.get(0));
        Employee workerBuh2 = new Employee("Марья", "Петрова", 96500f, DEPARTMENT_BY_ID.get(0));

        Employee workerIt1 = new Employee("Иван", "Петров", 200_000f, DEPARTMENT_BY_ID.get(1));
        Employee workerIt2 = new Employee("Крирл", "Петров", 180_500f, DEPARTMENT_BY_ID.get(1));
        Employee workerIt3 = new Employee("Инга", "Бергман", 80_000f, DEPARTMENT_BY_ID.get(1));

        Employee workerGlobal1 = new Employee("Артем", "Дзюба", 150_000, DEPARTMENT_BY_ID.get(2));
        Employee workerGlobal2 = new Employee("Далер", "Кузяев", 120_000, DEPARTMENT_BY_ID.get(2));
        Employee workerGlobal3 = new Employee("Ласина", "Траоре", 30_000, DEPARTMENT_BY_ID.get(2));
        Employee workerGlobal4 = new Employee("Сейду", "Думбия", 150_000, DEPARTMENT_BY_ID.get(2));

        employees.add(workerBuh);
        employees.add(workerBuh2);
        employees.add(workerIt1);
        employees.add(workerIt2);
        employees.add(workerIt3);

        employees.add(workerGlobal1);
        employees.add(workerGlobal2);
        employees.add(workerGlobal3);
        employees.add(workerGlobal4);

        when(employeeService.getAll()).thenReturn(employees);
        Map<String, List<Employee>> expectedEmployees = new HashMap<>();
        expectedEmployees.put(DEPARTMENT_BY_ID.get(departmentId).getName(), List.of(workerBuh, workerBuh2));

        Map<String, List<Employee>> actualEmployees = departmentService.getAll(departmentId);
        assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    void getAll_departmentIdIsNULL(){

        Integer departmentId = null;

        List<Employee> employees = new ArrayList<>();
        Employee workerBuh = new Employee("Инна", "Иванова", 70000f, DEPARTMENT_BY_ID.get(0));
        Employee workerBuh2 = new Employee("Марья", "Петрова", 96500f, DEPARTMENT_BY_ID.get(0));

        Employee workerIt1 = new Employee("Иван", "Петров", 200_000f, DEPARTMENT_BY_ID.get(1));
        Employee workerIt2 = new Employee("Крирл", "Петров", 180_500f, DEPARTMENT_BY_ID.get(1));
        Employee workerIt3 = new Employee("Инга", "Бергман", 80_000f, DEPARTMENT_BY_ID.get(1));

        employees.add(workerBuh);
        employees.add(workerBuh2);
        employees.add(workerIt1);
        employees.add(workerIt2);
        employees.add(workerIt3);

        when(employeeService.getAll()).thenReturn(employees);

        Map<String, List<Employee>> expectedEmployees = new HashMap<>();
        expectedEmployees.put(DEPARTMENT_BY_ID.get(0).getName(), List.of(workerBuh, workerBuh2));
        expectedEmployees.put(DEPARTMENT_BY_ID.get(1).getName(), List.of(workerIt1, workerIt2, workerIt3));

        Map<String, List<Employee>> actualEmployees = departmentService.getAll(departmentId);
        assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    void getAll_EmptyResult(){
        Integer departmentId = 2;

        List<Employee> employees = new ArrayList<>();
        Employee workerBuh = new Employee("Инна", "Иванова", 70000f, DEPARTMENT_BY_ID.get(0));
        Employee workerBuh2 = new Employee("Марья", "Петрова", 96500f, DEPARTMENT_BY_ID.get(0));

        Employee workerIt1 = new Employee("Иван", "Петров", 200_000f, DEPARTMENT_BY_ID.get(1));
        Employee workerIt2 = new Employee("Крирл", "Петров", 180_500f, DEPARTMENT_BY_ID.get(1));
        Employee workerIt3 = new Employee("Инга", "Бергман", 80_000f, DEPARTMENT_BY_ID.get(1));

        employees.add(workerBuh);
        employees.add(workerBuh2);
        employees.add(workerIt1);
        employees.add(workerIt2);
        employees.add(workerIt3);

        when(employeeService.getAll()).thenReturn(employees);

        Map<String, List<Employee>> expectedEmployees = new HashMap<>();

        Map<String, List<Employee>> actualEmployees = departmentService.getAll(departmentId);
        assertEquals(expectedEmployees, actualEmployees);
    }
}
