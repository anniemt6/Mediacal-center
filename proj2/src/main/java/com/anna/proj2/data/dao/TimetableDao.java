package com.anna.proj2.data.dao;

import com.anna.proj2.pojo.Timetable;

import java.util.Date;

public interface TimetableDao {

    Timetable getByIdAndDayOfAdmission(int id, Date dayOfAdmission);

    Timetable getById(int id);

    boolean create(Timetable timetable, int doctorId);

    boolean update(Timetable timetable);

    boolean delete(Timetable timetable);
}
