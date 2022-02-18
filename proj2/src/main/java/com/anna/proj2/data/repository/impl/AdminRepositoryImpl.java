package com.anna.proj2.data.repository.impl;

import com.anna.proj2.data.dao.DepartmentDao;
import com.anna.proj2.data.dao.DoctorOfficeDao;
import com.anna.proj2.data.dao.SpecialityDao;
import com.anna.proj2.data.repository.AdminRepository;
import com.anna.proj2.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminRepositoryImpl extends BaseRepositoryImpl implements AdminRepository {

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private SpecialityDao specialityDao;

    @Autowired
    private DoctorOfficeDao doctorOfficeDao;

    @Override
    public List<Department> getAllDepartments() {
        return departmentDao.getAll();
    }

    @Override
    public boolean saveDepartment(Department department) {
        return departmentDao.create(department);
    }

    @Override
    public boolean updateDepartment(Department department) {
        return departmentDao.update(department);
    }

    @Override
    public boolean deleteDepartment(Department department) {
        return departmentDao.delete(department);
    }

    @Override
    public List<DoctorOffice> getAllDoctorOffices() {
        return doctorOfficeDao.getAll();
    }

    @Override
    public boolean saveDoctorOffice(DoctorOffice doctorOffice) {
        return doctorOfficeDao.create(doctorOffice);
    }

    @Override
    public boolean updateDoctorOffice(DoctorOffice doctorOffice) {
        return doctorOfficeDao.update(doctorOffice);
    }

    @Override
    public boolean deleteDoctorOffice(DoctorOffice doctorOffice) {
        return doctorOfficeDao.delete(doctorOffice);
    }

    @Override
    public boolean updateMedicalCenter(MedicalCenter medicalCenter) {
        return medicalCenterDao.update(medicalCenter);
    }

    @Override
    public List<Speciality> getAllSpecialities() {
        return specialityDao.getAll();
    }

    @Override
    public boolean saveSpeciality(Speciality speciality) {
        return specialityDao.create(speciality);
    }

    @Override
    public boolean updateSpeciality(Speciality speciality) {
        return specialityDao.update(speciality);
    }

    @Override
    public boolean deleteSpeciality(Speciality speciality) {
        return specialityDao.delete(speciality);
    }

    @Override
    public int getVisitToDoctorAmount(int doctorId) {
        return doctorDao.getVisitToDoctorAmount(doctorId);
    }

    @Override
    public boolean saveDoctor(Doctor doctor) {
        return doctorDao.create(doctor);
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {
        return doctorDao.update(doctor);
    }

    @Override
    public boolean deleteDoctor(Doctor doctor) {
        return doctorDao.delete(doctor);
    }

    @Override
    public boolean saveTimetable(Timetable timetable, int doctorId) {
        return timetableDao.create(timetable, doctorId);
    }

    @Override
    public boolean updateTimetable(Timetable timetable) {
        return timetableDao.update(timetable);
    }

    @Override
    public boolean deleteTimetable(Timetable timetable) {
        return timetableDao.delete(timetable);
    }
}
