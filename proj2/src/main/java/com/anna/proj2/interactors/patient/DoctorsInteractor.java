package com.anna.proj2.interactors.patient;

import com.anna.proj2.pojo.Doctor;
import com.anna.proj2.pojo.Request;

import java.util.List;

public interface DoctorsInteractor extends RequestsInteractor {

    List<Doctor> getAllDoctors();

    List<Doctor> getDoctorByNameAndSurname(String name, String surname);

    List<Doctor> getDoctorBySpeciality(String speciality);

    List<Request> getDoctorRequests(int doctorId);

    List<Request> getDoctorRequestsByTimetableId(int doctorId, int timetableId);
}
