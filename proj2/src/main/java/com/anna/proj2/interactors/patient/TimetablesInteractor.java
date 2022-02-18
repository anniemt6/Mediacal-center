package com.anna.proj2.interactors.patient;

import com.anna.proj2.exceptions.NotExistsException;
import com.anna.proj2.pojo.Timetable;

import java.util.Date;
import java.util.List;

public interface TimetablesInteractor {

    Timetable getTimeTableByDoctorIdAndDayOfAdmission(int id, Date dayOfAdmission) throws NotExistsException;

    List<Timetable> getTimetablesByDoctorId(int id);
}
