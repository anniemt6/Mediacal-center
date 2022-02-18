package com.anna.proj2.data.utils.queries;

public class DoctorOfficeQueries {

    public static final String GET_ALL = "select * from doctor_office";

    public static final String CREATE = "insert into doctor_office (office_num) values (?)";

    public static final String UPDATE = "update doctor_office set office_num = ? where office_id = ?";

    public static final String DELETE = "delete from doctor_office where office_id = ?";
}


