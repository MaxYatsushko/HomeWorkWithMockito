package com.example.homeworkwithmockito.entity;

import java.util.HashMap;
import java.util.Map;

public class Department {
    private int id;

    private String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final Map<Integer, Department> DEPARTMENT_BY_ID;

    static {
        DEPARTMENT_BY_ID = new HashMap<>();
        DEPARTMENT_BY_ID.put(0, new Department(0, "Buh"));
        DEPARTMENT_BY_ID.put(1, new Department(1, "IT"));
        DEPARTMENT_BY_ID.put(2, new Department(2, "Global"));
        DEPARTMENT_BY_ID.put(3, new Department(3, "Another"));
    }
}
