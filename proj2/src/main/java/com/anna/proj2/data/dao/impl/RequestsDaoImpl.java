package com.anna.proj2.data.dao.impl;

import com.anna.proj2.data.dao.RequestsDao;
import com.anna.proj2.data.utils.DbConnector;
import com.anna.proj2.data.utils.DbService;
import com.anna.proj2.data.utils.mapping.DbMapper;
import com.anna.proj2.data.utils.queries.RequestQueries;
import com.anna.proj2.data.utils.queries.StoredProceduresAndFunctions;
import com.anna.proj2.pojo.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class RequestsDaoImpl implements RequestsDao {

    @Autowired
    private DbConnector dbConnector;

    @Override
    public List<Request> getByDoctorId(int id) {

        List<Request> result = new LinkedList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(RequestQueries.GET_BY_DOCTOR_ID);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(DbMapper.toRequest(resultSet));
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
    public List<Request> getByPatientId(int id) {

        List<Request> result = new LinkedList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(RequestQueries.GET_BY_PATIENT_ID);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(DbMapper.toRequest(resultSet));
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
    public void cancelRequest(int requestId) {

        Connection connection = null;
        CallableStatement statement = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareCall(StoredProceduresAndFunctions.CANCEL_REQUEST);
            statement.setInt(1, requestId);

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
             DbService.closeStatement(statement);
             DbService.closeConnection(connection);
        }
    }

    @Override
    public boolean create(Request obj) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(RequestQueries.CREATE);

            statement.setBoolean(1, obj.getVisit());
            statement.setTime(2, DbMapper.mapTimeToSql(obj.getVisitTime()));
            statement.setInt(3, obj.getPatientId());
            statement.setInt(4, obj.getTimetableId());
            statement.setInt(5, obj.getDoctorId());

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
    public boolean update(Request obj) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(RequestQueries.UPDATE);

            statement.setBoolean(1, obj.getVisit());
            statement.setTime(2, DbMapper.mapTimeToSql(obj.getVisitTime()));
            statement.setInt(3, obj.getTimetableId());
            statement.setInt(4, obj.getId());

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
    public boolean delete(Request obj) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(RequestQueries.DELETE);
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
