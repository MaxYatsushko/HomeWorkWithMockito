package com.example.homeworkwithmockito.services;

import com.example.homeworkwithmockito.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = {DepartmentServiceImplTest.class})
@ExtendWith(SpringExtension.class)
public class DepartmentServiceImplTest {


    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void getEmployeeWithMinSalary_success(){
        Integer departmentId = 1;

        Map<String, List<Employee>> expectedResult = new HashMap<>();
        expectedResult.put("Buh", Collections.emptyList());

        Map<String, List<Employee>> actualResult = departmentService.getAll(departmentId);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getEmployeeWithMinSalary_withDepartmentSearch(){

    }

    @Test
    void getEmployeeWithMaxSalary_success(){

    }

    @Test
    void getEmployeeWithMaxSalary_withDepartmentSearch(){

    }

    @Test
    void getAll_success(){

    }

    @Test
    void getAll_EmptyResult(){

    }
}
