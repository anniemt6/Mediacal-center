package com.anna.proj2.data.utils.queries;

public class SpecialityQueries {

    public static final String GET_ALL = "select * from speciality";

    public static final String CREATE = "insert into speciality (doctor_speciality) values (?)";

    public static final String UPDATE = "update speciality set doctor_speciality = ? where speciality_id = ?";

    public static final String DELETE = "delete from speciality where speciality_id = ?";
}
