package com.anna.proj2.data.utils.mapping;

import com.anna.proj2.pojo.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbMapper {

    public static Doctor toDoctor(ResultSet result) throws SQLException {

        return new Doctor(
                result.getInt(1),
                result.getString(2),
                result.getString(3),
                result.getString(4),
                result.getString(5),
                null,
                result.getString(6),
                result.getString(7),
                result.getString(8),
                result.getString(9),
                result.getString(10)
        );
    }

    public static DoctorData toDoctorData(ResultSet result) throws SQLException {

        return new DoctorData(
                result.getInt(1),
                result.getInt(2),
                result.getInt(3),
                result.getInt(4)
        );
    }

    public static Department toDepartment(ResultSet result) throws SQLException {
        return new Department(result.getInt(1), result.getString(2));
    }

    public static DoctorOffice toDoctorOffice(ResultSet result) throws SQLException {
        return new DoctorOffice(result.getInt(1), result.getString(2));
    }

    public static Patient toPatient(ResultSet result) throws SQLException {

        return new Patient(
                result.getInt(1),
                result.getString(2),
                result.getString(3),
                result.getString(4),
                result.getString(5),
                result.getDate(8),
                result.getString(9),
                result.getString(10),
                result.getString(7),
                result.getString(6),
                result.getInt(11)
        );
    }

    public static Request toRequest(ResultSet result) throws SQLException {

        return new Request(
                result.getInt(1),
                result.getBoolean(2),
                mapTimeFromSql(result.getTime(3)),
                result.getInt(4),
                result.getInt(5),
                result.getInt(6)
        );
    }

    public static Speciality toSpeciality(ResultSet result) throws SQLException {
        return new Speciality(result.getInt(1), result.getString(2));
    }

    public static Timetable toTimetable(ResultSet result) throws SQLException {

        return new Timetable(
                result.getInt(1),
                result.getDate(2),
                mapTimeFromSql(result.getTime(3)),
                mapTimeFromSql(result.getTime(4))
        );
    }

    public static MedicalCenter toMedicalCenter(ResultSet result) throws SQLException {

        return new MedicalCenter(
                result.getInt(1),
                result.getString(2),
                result.getString(3),
                result.getString(4),
                result.getString(5)
        );
    }

    public static java.sql.Date mapToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static com.anna.proj2.pojo.Time mapTimeFromSql(java.sql.Time time) {
        return new com.anna.proj2.pojo.Time(
                Byte.parseByte(time.toString().split(":")[0]),
                Byte.parseByte(time.toString().split(":")[1])
        );
    }

    public static java.sql.Time mapTimeToSql(com.anna.proj2.pojo.Time time) {
        return new java.sql.Time(time.getHours(), time.getMinutes(), 0);
    }
}
