package com.anna.proj2.data.utils.queries;

public class TimetableQueries {

    public static final String GET_BY_ID_AND_DAY_OF_ADMISSION = "select * from timetable where timetable_id = ? and day_of_admission = ?";

    public static final String GET_BY_ID = "select * from timetable where timetable_id = ?";

    public static final String GET_NEW_TIMETABLE_ID = "select timetable_id from timetable where timetable_id not in (select timetable_id from doctor_timetable)";

    public static final String CREATE = "insert into timetable (day_of_admission, start_time, end_time) values (?, ?, ?)";

    public static final String UPDATE = "update timetable set day_of_admission = ?, start_time = ?, end_time = ? where timetable_id = ?";

    public static final String DELETE = "delete from timetable where timetable_id = ?";
}
