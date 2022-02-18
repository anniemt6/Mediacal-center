package com.anna.proj2.data.utils.queries;

public class RequestQueries {

    public static final String GET_BY_DOCTOR_ID = "select * from request where doctor_id = ?";

    public static final String GET_BY_PATIENT_ID = "select * from request where patient_id = ?";

    public static final String CREATE = "insert into request (visit, visit_time, patient_id, timetable_id, doctor_id) values (?, ?, ?, ?, ?)";

    public static final String UPDATE = "update request set visit = ?, visit_time = ?, timetable_id = ? where request_id = ?";

    public static final String DELETE = "delete from request where id = ?";
}


