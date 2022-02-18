package com.anna.proj2.interactors.patient;

import com.anna.proj2.exceptions.*;
import com.anna.proj2.pojo.Patient;
import com.anna.proj2.pojo.Request;
import com.anna.proj2.pojo.Time;
import com.anna.proj2.pojo.Timetable;

import java.util.List;

public interface PatientsInteractor extends RequestsInteractor {

    Patient getPatientById(int id) throws NotExistsException;

    void updatePatient(Patient patient) throws ValidationException, UnableToUpdateException;

    void deletePatient(Patient patient) throws UnableToDeleteException;

    List<Request> getPatientRequests(int patientId);

    void saveRequest(Request request) throws ValidationException, UnableToSaveException;

    void deleteRequest(Request request) throws UnableToDeleteException;

    void cancelRequest(int requestId);

    List<Time> getAvailableRequestTime(Timetable timetable, int doctorId);
}
