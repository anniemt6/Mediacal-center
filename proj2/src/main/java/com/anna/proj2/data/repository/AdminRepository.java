package com.anna.proj2.data.repository;

import com.anna.proj2.pojo.*;

import java.util.List;

public interface AdminRepository extends BaseRepository {

    List<Department> getAllDepartments();
    boolean saveDepartment(Department department);
    boolean updateDepartment(Department department);
    boolean deleteDepartment(Department department);


    List<DoctorOffice> getAllDoctorOffices();
    boolean saveDoctorOffice(DoctorOffice doctorOffice);
    boolean updateDoctorOffice(DoctorOffice doctorOffice);
    boolean deleteDoctorOffice(DoctorOffice doctorOffice);

    boolean updateMedicalCenter(MedicalCenter medicalCenter);

    List<Speciality> getAllSpecialities();
    boolean saveSpeciality(Speciality speciality);
    boolean updateSpeciality(Speciality speciality);
    boolean deleteSpeciality(Speciality speciality);

    boolean saveDoctor(Doctor doctor);
    boolean updateDoctor(Doctor doctor);
    boolean deleteDoctor(Doctor doctor);
    int getVisitToDoctorAmount(int doctorId);

    boolean saveTimetable(Timetable timetable, int doctorId);
    boolean updateTimetable(Timetable timetable);
    boolean deleteTimetable(Timetable timetable);
}
