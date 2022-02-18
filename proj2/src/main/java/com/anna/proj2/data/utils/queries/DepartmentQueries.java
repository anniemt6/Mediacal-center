package com.anna.proj2.data.utils.queries;

public class DepartmentQueries {

    public static final String GET_ALL = "select * from department";

    public static final String CREATE = "insert into department (department_name) values (?)";

    public static final String UPDATE = "update department set department_name = ? where department_id = ?";

    public static final String DELETE = "delete from department where department_id = ?";
}
