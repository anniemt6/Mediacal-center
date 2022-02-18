package com.anna.proj2.interactors.admin;

import com.anna.proj2.exceptions.UnableToDeleteException;
import com.anna.proj2.exceptions.UnableToSaveException;
import com.anna.proj2.exceptions.UnableToUpdateException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.interactors.patient.TimetablesInteractor;
import com.anna.proj2.pojo.Timetable;

public interface TimetablesAdminInteractor extends TimetablesInteractor {

    void saveTimetable(Timetable timetable, int doctorId) throws ValidationException, UnableToSaveException;

    void updateTimetable(Timetable timetable) throws ValidationException, UnableToUpdateException;

    void deleteTimetable(Timetable timetable) throws UnableToDeleteException;
}
