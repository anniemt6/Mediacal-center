package com.anna.proj2.data.dao.impl;

import com.anna.proj2.data.utils.queries.StoredProceduresAndFunctions;
import com.anna.proj2.pojo.Doctor;
import com.anna.proj2.data.dao.DoctorDao;
import com.anna.proj2.data.utils.DbConnector;
import com.anna.proj2.data.utils.DbService;
import com.anna.proj2.data.utils.mapping.DbMapper;
import com.anna.proj2.data.utils.queries.DoctorQueries;
import com.anna.proj2.pojo.DoctorData;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class DoctorDaoImpl implements DoctorDao {

    @Autowired
    private DbConnector dbConnector;

    @Override
    public List<Doctor> getAll() {

        List<Doctor> result = new LinkedList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery(DoctorQueries.GET_ALL);

            while (resultSet.next()) {
                result.add(DbMapper.toDoctor(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbService.closeResultSet(resultSet);
            DbService.closeStatement(statement);
            DbService.closeConnection(connection);
        }

        return result;
    }

    @Override
    public List<Doctor> getByNameAndSurname(String name, String surname) {

        List<Doctor> result = new LinkedList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(DoctorQueries.GET_BY_NAME_AND_SURNAME);

            statement.setString(1, name);
            statement.setString(2, surname);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(DbMapper.toDoctor(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbService.closeResultSet(resultSet);
            DbService.closeStatement(statement);
            DbService.closeConnection(connection);
        }

        return result;
    }

    @Override
    public List<Doctor> getBySpeciality(String speciality) {

        List<Doctor> result = new LinkedList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(DoctorQueries.GET_BY_SPECIALITY);

            statement.setString(1, speciality);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(DbMapper.toDoctor(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbService.closeResultSet(resultSet);
            DbService.closeStatement(statement);
            DbService.closeConnection(connection);
        }

        return result;
    }

    @Override
    public Doctor getByPasswordAndEmail(String password, String email) {

        Doctor doctor = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(DoctorQueries.GET_BY_PASSWORD_AND_EMAIL);

            statement.setString(1, email);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                doctor = DbMapper.toDoctor(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbService.closeResultSet(resultSet);
            DbService.closeStatement(statement);
            DbService.closeConnection(connection);
        }

        return doctor;
    }

    @Override
    public int getVisitToDoctorAmount(int doctorId) {

        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareCall(StoredProceduresAndFunctions.VISIT_TO_DOCTOR_AMOUNT);

            statement.setInt(2, doctorId);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

            return 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbService.closeResultSet(resultSet);
            DbService.closeStatement(statement);
            DbService.closeConnection(connection);
        }

        return 0;
    }

    @Override
    public boolean create(Doctor obj) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(DoctorQueries.CREATE);

            DoctorData doctorData = getDoctorData(obj);

            if (doctorData == null) {
                return false;
            }

            statement.setString(1, obj.getFirstName());
            statement.setString(2, obj.getLastName());
            statement.setString(3, obj.getMiddleName());
            statement.setString(4, obj.getPhoneNum());
            statement.setString(5, obj.getDoctorEmail());
            statement.setString(6, obj.getDoctorPassword());
            statement.setInt(7, doctorData.getSpecialityId());
            statement.setInt(8, doctorData.getMedId());
            statement.setInt(9, doctorData.getDepartmentId());
            statement.setInt(10, doctorData.getOfficeId());

            return statement.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbService.closeStatement(statement);
            DbService.closeConnection(connection);
        }

        return false;
    }

    @Override
    public boolean update(Doctor obj) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(DoctorQueries.UPDATE);

            DoctorData doctorData = getDoctorData(obj);

            if (doctorData == null) {
                return false;
            }

            statement.setString(1, obj.getFirstName());
            statement.setString(2, obj.getLastName());
            statement.setString(3, obj.getMiddleName());
            statement.setString(4, obj.getPhoneNum());
            statement.setString(5, obj.getDoctorEmail());
            statement.setString(6, obj.getDoctorPassword());
            statement.setInt(7, doctorData.getSpecialityId());
            statement.setInt(8, doctorData.getMedId());
            statement.setInt(9, doctorData.getDepartmentId());
            statement.setInt(10, doctorData.getOfficeId());
            statement.setInt(11, obj.getId());

            return statement.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbService.closeStatement(statement);
            DbService.closeConnection(connection);
        }

        return false;
    }

    @Override
    public boolean delete(Doctor obj) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(DoctorQueries.DELETE);

            statement.setInt(1, obj.getId());

            return statement.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbService.closeStatement(statement);
            DbService.closeConnection(connection);
        }

        return false;
    }

    private DoctorData getDoctorData(Doctor doctor) {

        DoctorData doctorData = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(DoctorQueries.GET_DOCTOR_DATA);

            statement.setString(1, doctor.getOfficeNumber());
            statement.setString(2, doctor.getDepartmentName());
            statement.setString(3, doctor.getSpeciality());
            statement.setString(4, doctor.getMedicalCenterName());

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                doctorData = DbMapper.toDoctorData(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbService.closeResultSet(resultSet);
            DbService.closeStatement(statement);
            DbService.closeConnection(connection);
        }

        return doctorData;
    }
}