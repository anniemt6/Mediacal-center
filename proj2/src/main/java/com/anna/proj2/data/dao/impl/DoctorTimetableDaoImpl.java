package com.anna.proj2.data.dao.impl;

import com.anna.proj2.data.dao.DoctorTimetableDao;
import com.anna.proj2.data.utils.DbConnector;
import com.anna.proj2.data.utils.DbService;
import com.anna.proj2.data.utils.queries.DoctorTimetableQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Component
public class DoctorTimetableDaoImpl implements DoctorTimetableDao {

    @Autowired
    private DbConnector dbConnector;

    @Override
    public List<Integer> getTimeTableIdsByDoctorId(int id) {

        List<Integer> result = new LinkedList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(DoctorTimetableQueries.GET_TIMETABLE_ID_BY_DOCTOR_ID);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(resultSet.getInt(1));
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
