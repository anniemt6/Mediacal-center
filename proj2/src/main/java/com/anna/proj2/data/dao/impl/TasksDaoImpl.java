package com.anna.proj2.data.dao.impl;

import com.anna.proj2.data.dao.TasksDao;
import com.anna.proj2.data.utils.DbConnector;
import com.anna.proj2.data.utils.DbService;
import com.anna.proj2.data.utils.queries.TasksQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class TasksDaoImpl implements TasksDao {

    @Autowired
    private DbConnector dbConnector;

    @Override
    public List<String> performTask1() {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String resultString = "";
        List<String> result = new LinkedList<>();

        try {

            connection = dbConnector.getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery(TasksQueries.TASK_1);

            while (resultSet.next()) {
                resultString += resultSet.getInt(1) + " ";
                resultString += resultSet.getString(2) + " ";
                resultString += resultSet.getString(3) + " ";
                resultString += resultSet.getString(4) + " ";
                resultString += resultSet.getString(5) + " ";
                resultString += resultSet.getString(6) + " ";
                resultString += resultSet.getString(7) + " ";
                result.add(resultString);
                resultString = "";
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
    public Object performTask2() {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List result = new LinkedList();

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(TasksQueries.TASK_2);

            // TODO("Set data to statement")

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(null/*DbMapper.to...*/);
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
    public Object performTask3() {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List result = new LinkedList();

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(TasksQueries.TASK_1);

            // TODO("Set data to statement")

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(null/*DbMapper.to...*/);
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
}
