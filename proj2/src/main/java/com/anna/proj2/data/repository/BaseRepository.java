package com.anna.proj2.data.repository;

import com.anna.proj2.pojo.Doctor;
import com.anna.proj2.pojo.MedicalCenter;
import com.anna.proj2.pojo.Timetable;

import java.util.Date;
import java.util.List;

public interface BaseRepository {

    List<Doctor> getAllDoctors();
    List<Doctor> getDoctorByNameAndSurname(String name, String surname);

    MedicalCenter getMedicalCenterInfo();

    Timetable getTimetableById(int id);
    Timetable getTimetableByDoctorIdAndDayOfAdmission(int id, Date dayOfAdmission);
    List<Timetable> getTimetablesByDoctorId(int id);
}
