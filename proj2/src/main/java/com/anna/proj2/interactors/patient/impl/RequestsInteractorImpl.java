package com.anna.proj2.interactors.patient.impl;

import com.anna.proj2.data.repository.MainRepository;
import com.anna.proj2.exceptions.UnableToUpdateException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.interactors.patient.RequestsInteractor;
import com.anna.proj2.pojo.Request;
import com.anna.proj2.pojo.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
abstract public class RequestsInteractorImpl implements RequestsInteractor {

    @Autowired
    private MainRepository repository;

    @Override
    public void updateRequest(Request request) throws ValidationException, UnableToUpdateException {

        if (!validateRequest(request)) {
            throw new ValidationException();
        }

        if (!repository.updateRequest(request)) {
            throw new UnableToUpdateException();
        }
    }

    protected boolean validateRequest(Request request) {

        List<Request> requests = repository.getRequestsByDoctorId(request.getDoctorId());
        requests.addAll(repository.getRequestsByPatientId(request.getPatientId()));

        Timetable timetable;
        try {
            timetable = repository
                    .getTimetablesByDoctorId(request.getDoctorId())
                    .stream()
                    .filter(t -> t.getId() == request.getTimetableId())
                    .collect(Collectors.toList())
                    .get(0);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

        return requests
                .stream()
                .noneMatch(r ->
                        request.getTimetableId() == r.getTimetableId() &&
                                request.getVisitTime().equals(r.getVisitTime()) &&
                                request.getId() != r.getId()
                ) &&
                request.getVisitTime().compareTo(timetable.getStartTime()) >= 0 &&
                request.getVisitTime().compareTo(timetable.getEndTime()) < 0;
    }
}
