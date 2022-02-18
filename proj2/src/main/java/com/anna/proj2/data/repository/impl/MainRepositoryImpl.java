package com.anna.proj2.data.repository.impl;

import com.anna.proj2.data.dao.PatientDao;
import com.anna.proj2.data.dao.RequestsDao;
import com.anna.proj2.data.repository.MainRepository;
import com.anna.proj2.pojo.Doctor;
import com.anna.proj2.pojo.Patient;
import com.anna.proj2.pojo.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MainRepositoryImpl extends BaseRepositoryImpl implements MainRepository {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private RequestsDao requestsDao;

    @Override
    public List<Doctor> getDoctorBySpeciality(String speciality) {
        return doctorDao.getBySpeciality(speciality);
    }

    @Override
    public Doctor getDoctorByEmailAndPassword(String password, String email) {
        return doctorDao.getByPasswordAndEmail(password, email);
    }

    @Override
    public Patient getPatientById(int id) {
        return patientDao.getById(id);
    }

    @Override
    public Patient getPatientByEmailAndPassword(String email, String password) {
        return patientDao.getByEmailAndPassword(email, password);
    }

    @Override
    public boolean savePatient(Patient patient) {
        return patientDao.create(patient);
    }

    @Override
    public boolean updatePatient(Patient patient) {
        return patientDao.update(patient);
    }

    @Override
    public boolean deletePatient(Patient patient) {
        return patientDao.delete(patient);
    }

    @Override
    public List<Request> getRequestsByDoctorId(int id) {
        return requestsDao.getByDoctorId(id);
    }

    @Override
    public List<Request> getRequestsByPatientId(int id) {
        return requestsDao.getByPatientId(id);
    }

    @Override
    public boolean saveRequest(Request request) {
        return requestsDao.create(request);
    }

    @Override
    public boolean updateRequest(Request request) {
        return requestsDao.update(request);
    }

    @Override
    public boolean deleteRequest(Request request) {
        return requestsDao.delete(request);
    }

    @Override
    public void cancelRequest(int requestId) {
        requestsDao.cancelRequest(requestId);
    }
}
