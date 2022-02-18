package com.anna.proj2.data.dao.impl;

import com.anna.proj2.data.dao.DoctorOfficeDao;
import com.anna.proj2.data.utils.DbConnector;
import com.anna.proj2.data.utils.DbService;
import com.anna.proj2.data.utils.mapping.DbMapper;
import com.anna.proj2.data.utils.queries.DoctorOfficeQueries;
import com.anna.proj2.pojo.DoctorOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class DoctorOfficeDaoImpl implements DoctorOfficeDao {

    @Autowired
    DbConnector dbConnector;

    @Override
    public List<DoctorOffice> getAll() {

        List<DoctorOffice> result = new LinkedList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery(DoctorOfficeQueries.GET_ALL);

            while (resultSet.next()) {
                result.add(DbMapper.toDoctorOffice(resultSet));
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
    public boolean create(DoctorOffice obj) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(DoctorOfficeQueries.CREATE);
            statement.setString(1, obj.getOfficeNum());

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
    public boolean update(DoctorOffice obj) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

             connection = dbConnector.getConnection();
             statement = connection.prepareStatement(DoctorOfficeQueries.UPDATE);
             statement.setString(1, obj.getOfficeNum());
             statement.setInt(2, obj.getId());

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
    public boolean delete(DoctorOffice obj) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(DoctorOfficeQueries.DELETE);
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
