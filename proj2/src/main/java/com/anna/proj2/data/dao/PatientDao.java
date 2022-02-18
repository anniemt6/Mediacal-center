package com.anna.proj2.data.dao;

import com.anna.proj2.pojo.Patient;

public interface PatientDao extends BasicDao<Patient> {

    Patient getById(int id);

    Patient getByEmailAndPassword(String email, String password);
}
