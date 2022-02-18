package com.anna.proj2.interactors.patient.impl;

import com.anna.proj2.data.repository.MainRepository;
import com.anna.proj2.exceptions.*;
import com.anna.proj2.interactors.patient.PatientsInteractor;
import com.anna.proj2.pojo.Patient;
import com.anna.proj2.pojo.Request;
import com.anna.proj2.pojo.Time;
import com.anna.proj2.pojo.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientsInteractorImpl extends RequestsInteractorImpl implements PatientsInteractor {

    @Autowired
    private MainRepository repository;

    @Override
    public Patient getPatientById(int id) throws NotExistsException {

        Patient patient = repository.getPatientById(id);

        if (patient != null) {
            return patient;
        } else {
            throw new NotExistsException();
        }
    }

    @Override
    public void updatePatient(Patient patient) throws ValidationException, UnableToUpdateException {

        if (!validatePatient(patient)) {
            throw new ValidationException();
        }

        if (!repository.updatePatient(patient)) {
            throw new UnableToUpdateException();
        }
    }

    @Override
    public void deletePatient(Patient patient) throws UnableToDeleteException {

        if (!repository.deletePatient(patient)) {
            throw new UnableToDeleteException();
        }
    }

    @Override
    public List<Request> getPatientRequests(int patientId) {

        return repository
                .getRequestsByPatientId(patientId)
                .stream()
                .filter(request -> !request.getVisit() && repository
                        .getTimetableById(request.getTimetableId())
                        .getDayOfAdmission()
                        .after(new Date()))
                .collect(Collectors.toList());
    }

    @Override
    public void saveRequest(Request request) throws ValidationException, UnableToSaveException {

        if (!validateRequest(request)) {
            throw new ValidationException();
        }

        if (!repository.saveRequest(request)) {
            throw new UnableToSaveException();
        }
    }

    @Override
    public void deleteRequest(Request request) throws UnableToDeleteException {

        if (!repository.deleteRequest(request)) {
            throw new UnableToDeleteException();
        }
    }

    @Override
    public void cancelRequest(int requestId) {
        repository.cancelRequest(requestId);
    }

    @Override
    public List<Time> getAvailableRequestTime(Timetable timetable, int doctorId) {

        List<Request> requests = repository
                .getRequestsByDoctorId(doctorId)
                .stream()
                .filter(r -> r.getTimetableId() == timetable.getId())
                .collect(Collectors.toList());

        List<Time> times = new LinkedList<>();
        times.add(timetable.getStartTime());

        while (times.get(times.size() - 1).compareTo(timetable.getEndTime()) < 0) {
            times.add(times.get(times.size() - 1).addMinutes(10));
        }

        return times
                .stream()
                .filter(time -> {
                    for (Request request : requests) {
                        if (request.getVisitTime().equals(time)) {
                            return false;
                        }
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }

    private boolean validatePatient(Patient patient) {
        return patient.getFirstName().length() > 2 &&
                patient.getLastName().length() > 2 &&
                patient.getMiddleName().length() > 2 &&
                patient.getPatientEmail().matches("^(.+)@(.+)\\.(.+)$") &&
                patient.getPatientPassword().length() > 8;
    }
}
