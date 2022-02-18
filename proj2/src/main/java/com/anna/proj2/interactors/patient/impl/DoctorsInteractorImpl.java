package com.anna.proj2.interactors.patient.impl;

import com.anna.proj2.data.repository.MainRepository;
import com.anna.proj2.interactors.patient.DoctorsInteractor;
import com.anna.proj2.pojo.Doctor;
import com.anna.proj2.pojo.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("doctorsInteractor")
public class DoctorsInteractorImpl extends RequestsInteractorImpl implements DoctorsInteractor {

    @Autowired
    private MainRepository repository;

    @Override
    public List<Doctor> getAllDoctors() {
        return repository.getAllDoctors();
    }

    @Override
    public List<Doctor> getDoctorByNameAndSurname(String name, String surname) {
        return repository.getDoctorByNameAndSurname(name, surname);
    }

    @Override
    public List<Doctor> getDoctorBySpeciality(String speciality) {
        return repository.getDoctorBySpeciality(speciality);
    }

    @Override
    public List<Request> getDoctorRequests(int doctorId) {
        return repository.getRequestsByDoctorId(doctorId);
    }

    @Override
    public List<Request> getDoctorRequestsByTimetableId(int doctorId, int timetableId) {

        return repository.getRequestsByDoctorId(doctorId)
                .stream()
                .filter(request -> request.getTimetableId() == timetableId)
                .collect(Collectors.toList());
    }
}
