package com.anna.proj2.data.dao.impl;

import com.anna.proj2.data.dao.PatientDao;
import com.anna.proj2.data.utils.DbConnector;
import com.anna.proj2.data.utils.DbService;
import com.anna.proj2.data.utils.mapping.DbMapper;
import com.anna.proj2.data.utils.queries.PatientQueries;
import com.anna.proj2.pojo.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PatientDaoImpl implements PatientDao {

    @Autowired
    private DbConnector dbConnector;

    @Override
    public Patient getById(int id) {

        Patient patient = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(PatientQueries.GET_BY_ID);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                patient = DbMapper.toPatient(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbService.closeResultSet(resultSet);
            DbService.closeStatement(statement);
            DbService.closeConnection(connection);
        }

        return patient;
    }

    @Override
    public Patient getByEmailAndPassword(String email, String password) {

        Patient patient = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(PatientQueries.GET_BY_EMAIL_AND_PASSWORD);
            statement.setString(1, email);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                patient = DbMapper.toPatient(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbService.closeResultSet(resultSet);
            DbService.closeStatement(statement);
            DbService.closeConnection(connection);
        }

        return patient;
    }

    @Override
    public boolean create(Patient obj) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(PatientQueries.CREATE);

            statement.setString(1, obj.getFirstName());
            statement.setString(2, obj.getLastName());
            statement.setString(3, obj.getMiddleName());
            statement.setString(4, obj.getPhoneNum());
            statement.setString(5, obj.getPatientEmail());
            statement.setString(6, obj.getPatientPassword());
            statement.setDate(7, DbMapper.mapToSqlDate(obj.getDateOfBirth()));
            statement.setString(8, obj.getAddress());
            statement.setString(9, obj.getSex());
            statement.setInt(10, obj.getMedicalHistoryNum());

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
    public boolean update(Patient obj) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(PatientQueries.UPDATE);

            statement.setString(1, obj.getFirstName());
            statement.setString(2, obj.getLastName());
            statement.setString(3, obj.getMiddleName());
            statement.setString(4, obj.getPhoneNum());
            statement.setString(5, obj.getPatientEmail());
            statement.setString(6, obj.getPatientPassword());
            statement.setDate(7, DbMapper.mapToSqlDate(obj.getDateOfBirth()));
            statement.setString(8, obj.getAddress());
            statement.setString(9, obj.getSex());
            statement.setInt(10, obj.getMedicalHistoryNum());
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
    public boolean delete(Patient obj) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(PatientQueries.DELETE);
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
}
