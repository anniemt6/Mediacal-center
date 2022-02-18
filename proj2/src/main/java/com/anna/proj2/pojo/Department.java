package com.anna.proj2.pojo;

public class Department {

    private final int id;
    private String departmentName;

    public Department(int id, String departmentName){
        this.id = id;
        this.departmentName = departmentName;
    }

    public int getId() {
        return id;
    }

    public String getDepartmentName(){
        return departmentName;
    }
    public void setDepartmentName(String departmentName){
        this.departmentName = departmentName;
    }
}
