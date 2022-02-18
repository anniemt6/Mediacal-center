package com.anna.proj2.data.repository.impl;

import com.anna.proj2.data.dao.DoctorDao;
import com.anna.proj2.data.dao.DoctorTimetableDao;
import com.anna.proj2.data.dao.MedicalCenterDao;
import com.anna.proj2.data.dao.TimetableDao;
import com.anna.proj2.data.repository.BaseRepository;
import com.anna.proj2.pojo.Doctor;
import com.anna.proj2.pojo.MedicalCenter;
import com.anna.proj2.pojo.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component
public abstract class BaseRepositoryImpl implements BaseRepository {

    @Autowired
    protected DoctorDao doctorDao;

    @Autowired
    protected MedicalCenterDao medicalCenterDao;

    @Autowired
    protected TimetableDao timetableDao;

    @Autowired
    protected DoctorTimetableDao doctorTimetableDao;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorDao.getAll();
    }

    @Override
    public List<Doctor> getDoctorByNameAndSurname(String name, String surname) {
        return doctorDao.getByNameAndSurname(name, surname);
    }

    @Override
    public MedicalCenter getMedicalCenterInfo() {
        return medicalCenterDao.getMedicalCenter();
    }

    @Override
    public Timetable getTimetableById(int id) {
        return timetableDao.getById(id);
    }

    @Override
    public Timetable getTimetableByDoctorIdAndDayOfAdmission(int id, Date dayOfAdmission) {

        Timetable timetable = null;

        List<Integer> timetableIds = getTimetableIdsByDoctorId(id);

        for (int timetableId : timetableIds) {

            timetable = timetableDao.getByIdAndDayOfAdmission(timetableId, dayOfAdmission);

            if (timetable != null) {
                break;
            }
        }

        return timetable;
    }

    @Override
    public List<Timetable> getTimetablesByDoctorId(int id) {

        List<Timetable> result = new LinkedList<>();

        List<Integer> timetableIds = getTimetableIdsByDoctorId(id);

        for (int timetableId : timetableIds) {
            result.add(timetableDao.getById(timetableId));
        }

        return result;
    }

    private List<Integer> getTimetableIdsByDoctorId(int id) {
        return doctorTimetableDao.getTimeTableIdsByDoctorId(id);
    }
}
