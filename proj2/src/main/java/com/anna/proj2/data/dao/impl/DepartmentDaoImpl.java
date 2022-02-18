package com.anna.proj2.data.dao.impl;

import com.anna.proj2.data.dao.DepartmentDao;
import com.anna.proj2.data.utils.DbConnector;
import com.anna.proj2.data.utils.DbService;
import com.anna.proj2.data.utils.mapping.DbMapper;
import com.anna.proj2.data.utils.queries.DepartmentQueries;
import com.anna.proj2.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class DepartmentDaoImpl implements DepartmentDao {

    @Autowired
    private DbConnector dbConnector;

    @Override
    public List<Department> getAll() {

        List<Department> result = new LinkedList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery(DepartmentQueries.GET_ALL);

            while (resultSet.next()) {
                result.add(DbMapper.toDepartment(resultSet));
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
    public boolean create(Department obj) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(DepartmentQueries.CREATE);
            statement.setString(1, obj.getDepartmentName());

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
    public boolean update(Department obj) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(DepartmentQueries.UPDATE);
            statement.setString(1, obj.getDepartmentName());
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
    public boolean delete(Department obj) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(DepartmentQueries.DELETE);
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
