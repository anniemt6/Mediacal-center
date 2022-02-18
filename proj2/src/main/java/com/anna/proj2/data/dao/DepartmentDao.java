package com.anna.proj2.data.dao;

import com.anna.proj2.pojo.Department;

import java.util.List;

public interface DepartmentDao extends BasicDao<Department> {

    List<Department> getAll();
}
