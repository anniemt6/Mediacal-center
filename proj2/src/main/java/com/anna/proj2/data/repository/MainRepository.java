package com.anna.proj2.data.repository;

import com.anna.proj2.pojo.Doctor;
import com.anna.proj2.pojo.Patient;
import com.anna.proj2.pojo.Request;

import java.util.List;

public interface MainRepository extends BaseRepository {

    List<Doctor> getDoctorBySpeciality(String speciality);
    Doctor getDoctorByEmailAndPassword(String password, String email);

    Patient getPatientById(int id);
    Patient getPatientByEmailAndPassword(String email, String password);
    boolean savePatient(Patient patient);
    boolean updatePatient(Patient patient);
    boolean deletePatient(Patient patient);

    List<Request> getRequestsByDoctorId(int id);
    List<Request> getRequestsByPatientId(int id);
    boolean saveRequest(Request request);
    boolean updateRequest(Request request);
    boolean deleteRequest(Request request);
    void cancelRequest(int requestId);
}
