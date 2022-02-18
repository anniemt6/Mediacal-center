package com.anna.proj2.interactors.admin.impl;

import com.anna.proj2.data.repository.AdminRepository;
import com.anna.proj2.exceptions.UnableToDeleteException;
import com.anna.proj2.exceptions.UnableToSaveException;
import com.anna.proj2.exceptions.UnableToUpdateException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.interactors.admin.TimetablesAdminInteractor;
import com.anna.proj2.interactors.patient.impl.TimetablesInteractorImpl;
import com.anna.proj2.pojo.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TimetablesAdminInteractorImpl extends TimetablesInteractorImpl implements TimetablesAdminInteractor {

    @Autowired
    private AdminRepository repository;

    @Override
    public List<Timetable> getTimetablesByDoctorId(int id) {
        return repository.getTimetablesByDoctorId(id);
    }

    @Override
    public void saveTimetable(Timetable timetable, int doctorId) throws ValidationException, UnableToSaveException {

        if (!validate(timetable)) {
            throw new ValidationException();
        }

        if (!repository.saveTimetable(timetable, doctorId)) {
            throw new UnableToSaveException();
        }
    }

    @Override
    public void updateTimetable(Timetable timetable) throws ValidationException, UnableToUpdateException {

        if (!validate(timetable)) {
            throw new ValidationException();
        }

        if (!repository.updateTimetable(timetable)) {
            throw new UnableToUpdateException();
        }
    }

    @Override
    public void deleteTimetable(Timetable timetable) throws UnableToDeleteException {

        if (!repository.deleteTimetable(timetable)) {
            throw new UnableToDeleteException();
        }
    }

    private boolean validate(Timetable timetable) {
        return timetable.getDayOfAdmission().after(new Date()) &&
                timetable.getStartTime().compareTo(timetable.getEndTime()) < 0;
    }
}
