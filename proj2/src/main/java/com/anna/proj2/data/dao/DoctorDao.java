package com.anna.proj2.data.dao;

import com.anna.proj2.pojo.Doctor;

import java.util.List;

public interface DoctorDao extends BasicDao<Doctor> {

    List<Doctor> getAll();

    List<Doctor> getByNameAndSurname(String name, String surname);

    List<Doctor> getBySpeciality(String speciality);

    Doctor getByPasswordAndEmail(String password, String email);

    int getVisitToDoctorAmount(int doctorId);
}


