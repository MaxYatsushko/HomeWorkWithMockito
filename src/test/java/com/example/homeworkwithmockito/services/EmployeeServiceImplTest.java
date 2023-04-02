package com.example.homeworkwithmockito.services;

import com.example.homeworkwithmockito.entity.Department;
import com.example.homeworkwithmockito.entity.Employee;
import com.example.homeworkwithmockito.excetions.*;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.homeworkwithmockito.entity.Department.DEPARTMENT_BY_ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {EmployeeServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class EmployeeServiceImplTest {

    @Autowired
    private EmployeeService employeeService;

    @BeforeEach
    void initEmployees(){
        employeeService.init();
    }
    @Test
    void add_success(){
        Integer departmentId = 1;
        Employee employeeExpected = new Employee("Ivan", "Ivanov", 25000, DEPARTMENT_BY_ID.get(departmentId));
        List<Employee> employees = List.of(employeeExpected);

        Employee actualEmployee = employees.get(0);

        assertEquals(employeeExpected, actualEmployee);
    }

    @Test
    void add_EmployeeStorageIsFullException(){
        Integer departmentId = 1;
        List<Employee> employees = new ArrayList<>();
        employeeService.add("Ivan", "Ivanov", 35000, departmentId);
        employeeService.add("Petr", "Petrov", 25000, departmentId);
        employeeService.add("Petron", "Petrovan", 25000, departmentId);
        employeeService.add("Petrom", "Petrovan", 25000, departmentId);
        employeeService.add("Petroj", "Petrovan", 25000, departmentId);
        employeeService.add("Petrog", "Petrovan", 25000, departmentId);
        employeeService.add("Petrob", "Petrovan", 25000, departmentId);
        employeeService.add("Petroz", "Petrovan", 25000, departmentId);
        employeeService.add("Petrov", "Petrovan", 25000, departmentId);
        employeeService.add("Petroc", "Petrovan", 25000, departmentId);


        Exception exception = assertThrows(EmployeeStorageIsFullException.class, () -> employeeService.add("Ivane", "Ivanovec", 35000, departmentId));
        String expectedError = "Массив сотрудников переполнен";

        assertEquals(expectedError, exception.getMessage());
    }


    @Test
    void add_EmployeeAlreadyAddedException(){
        Integer departmentId = 1;
        employeeService.add("Ivan", "Ivanov", 35000, departmentId);
        employeeService.add("Petr", "Petrov", 25000, departmentId);
        employeeService.add("Petron", "Petrovan", 25000, departmentId);


        Exception exception = assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.add("Ivan", "Ivanov", 35000, departmentId));
        String expectedError = "В массиве уже есть такой сотрудник";

        assertEquals(expectedError, exception.getMessage());
    }

    @Test
    void add_CheckDataFirstNameAlpha(){
        Integer departmentId = 1;

        Exception exception = assertThrows(InvalidStringDataException.class, () -> employeeService.add("Ivan2", "Ivanov", 35000, departmentId));
        String expectedError = "Имя сотрудника не может быть пустым и должно содержать только буквы";

        assertEquals(expectedError, exception.getMessage());
    }

    @Test
    void add_CheckDataFirstNameRegistr(){
        Integer departmentId = 1;

        Exception exception = assertThrows(InvalidStringDataException.class, () -> employeeService.add("ivan", "Ivanov", 35000, departmentId));
        String expectedError = "Имя сотрудника должно начинаться с заглавной буквы";

        assertEquals(expectedError, exception.getMessage());
    }

    @Test
    void add_CheckDataLastNameAlpha(){
        Integer departmentId = 1;

        Exception exception = assertThrows(InvalidStringDataException.class, () -> employeeService.add("Ivan", "Ivanov2", 35000, departmentId));
        String expectedError = "Фамилия сотрудника не может быть пустой и должна содержать только буквы";

        assertEquals(expectedError, exception.getMessage());
    }

    @Test
    void add_CheckDataLastNameRegistr(){
        Integer departmentId = 1;

        Exception exception = assertThrows(InvalidStringDataException.class, () -> employeeService.add("Ivan", "ivanov", 35000, departmentId));
        String expectedError = "Фамилия сотрудника должна начинаться с заглавной буквы";

        assertEquals(expectedError, exception.getMessage());
    }

    @Test
    void find_EmployeeNotFoundException(){
        Integer departmentId = 1;

        Exception exception = assertThrows(EmployeeNotFoundException.class, () -> employeeService.find("Ivan", "Ivanov"));
        String expectedError = "Сотрудник не найден";

        assertEquals(expectedError, exception.getMessage());
    }

    @Test
    void find_success(){
        Integer departmentId = 0;
        Employee expectedEmployee = new Employee("Ivan", "Ivanov", 25000, new Department(departmentId, "Buh"));
        employeeService.add("Ivan", "Ivanov", 25000, departmentId);

        Employee actualEmployee = employeeService.find("Ivan", "Ivanov");

        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void remove_success(){
        Integer departmentId = 0;
        Employee expectedEmployee = new Employee("Ivan", "Ivanov", 25000, new Department(departmentId, "Buh"));
        employeeService.add("Ivan", "Ivanov", 25000, departmentId);

        Employee actualEmployee = employeeService.remove("Ivan", "Ivanov");

        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void getAll_success(){
        Integer departmentId = 0, salary = 25000;
        List<Employee> expectedEmployes = new ArrayList<>();
        String fistName = "Ivan", lastName = "Ivanov";
        Employee employee = new Employee(fistName, lastName, salary, new Department(departmentId, "Buh"));

        employeeService.add(fistName, lastName, salary, departmentId);
        expectedEmployes.add(employee);

        List<Employee> actualEmployees = employeeService.getAll();

        assertEquals(expectedEmployes, actualEmployees);
    }

    @Test
    void getAll_empty(){
        List<Employee> expectedEmployes = new ArrayList<>();

        List<Employee> actualEmployees = employeeService.getAll();

        assertEquals(expectedEmployes, actualEmployees);
    }
}