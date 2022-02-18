package com.anna.proj2.interactors.patient.impl;

import com.anna.proj2.data.repository.MainRepository;
import com.anna.proj2.exceptions.NotExistsException;
import com.anna.proj2.interactors.patient.TimetablesInteractor;
import com.anna.proj2.pojo.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("timetablesInteractor")
public class TimetablesInteractorImpl implements TimetablesInteractor {

    @Autowired
    private MainRepository repository;

    @Override
    public Timetable getTimeTableByDoctorIdAndDayOfAdmission(int id, Date dayOfAdmission) throws NotExistsException {

        Timetable timetable = repository.getTimetableByDoctorIdAndDayOfAdmission(id, dayOfAdmission);

        if (timetable != null) {
            return timetable;
        } else {
            throw new NotExistsException();
        }
    }

    @Override
    public List<Timetable> getTimetablesByDoctorId(int id) {

        List<Timetable> timetables = repository.getTimetablesByDoctorId(id);

        Calendar calendar = Calendar.getInstance();

        return timetables
                .stream()
                .filter(timetable -> {

                    Calendar timetableCalendar = Calendar.getInstance();
                    timetableCalendar.setTime(timetable.getDayOfAdmission());

                    return timetable.getDayOfAdmission().after(new Date()) ||
                            (timetableCalendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
                                    timetableCalendar.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR));
                })
                .collect(Collectors.toList());
    }
}
