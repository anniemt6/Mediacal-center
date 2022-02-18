package com.anna.proj2.controllers.util;

import com.anna.proj2.pojo.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeParser {

    private static final String pattern = "dd.MM.yyy";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

    public static String dateToString(Date date) {
        return dateFormat.format(date);
    }

    public static Date stringToDateIfFailureReturnNull(String date) {

        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String timeToString(Time time) {
        return time.toString();
    }

    public static Time stringToTimeIfFailureReturnNull(String time) {

        try {
            String[] splitResult = time.split(":");
            return new Time(Byte.parseByte(splitResult[0]), Byte.parseByte(splitResult[1]));

        } catch (Exception e) {
            return null;
        }
    }
}
