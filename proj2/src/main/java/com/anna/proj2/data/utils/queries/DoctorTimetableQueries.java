package com.anna.proj2.data.utils.queries;

public class DoctorTimetableQueries {

    public static final String GET_TIMETABLE_ID_BY_DOCTOR_ID = "select timetable_id from doctor_timetable where doctor_id = ?";

    public static final String CREATE = "insert into doctor_timetable(timetable_id, doctor_id) values (?, ?)";
}


