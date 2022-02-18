package com.anna.proj2.controllers.util;

import com.anna.proj2.pojo.Timetable;

public class TimetableParser {

    public static TimetableUi toTimetableUi(Timetable timetable) {

        return new TimetableUi(
                timetable.getId(),
                DateTimeParser.dateToString(timetable.getDayOfAdmission()),
                DateTimeParser.timeToString(timetable.getStartTime()),
                DateTimeParser.timeToString(timetable.getEndTime())
        );
    }

    public static Timetable toTimetable(TimetableUi timetableUi) {

        return new Timetable(
                timetableUi.getId(),
                DateTimeParser.stringToDateIfFailureReturnNull(timetableUi.getDayOfAdmission()),
                DateTimeParser.stringToTimeIfFailureReturnNull(timetableUi.getStartTime()),
                DateTimeParser.stringToTimeIfFailureReturnNull(timetableUi.getEndTime())
        );
    }
}
