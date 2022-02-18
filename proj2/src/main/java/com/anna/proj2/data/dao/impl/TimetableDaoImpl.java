package com.anna.proj2.data.dao.impl;

import com.anna.proj2.data.dao.TimetableDao;
import com.anna.proj2.data.utils.DbConnector;
import com.anna.proj2.data.utils.DbService;
import com.anna.proj2.data.utils.mapping.DbMapper;
import com.anna.proj2.data.utils.queries.DoctorTimetableQueries;
import com.anna.proj2.data.utils.queries.TimetableQueries;
import com.anna.proj2.pojo.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Date;

@Component
public class TimetableDaoImpl implements TimetableDao {

    @Autowired
    private DbConnector dbConnector;

    @Override
    public Timetable getByIdAndDayOfAdmission(int id, Date dayOfAdmission) {

        Timetable timetable = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(TimetableQueries.GET_BY_ID_AND_DAY_OF_ADMISSION);
            statement.setInt(1, id);
            statement.setDate(2, DbMapper.mapToSqlDate(dayOfAdmission));

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                timetable = DbMapper.toTimetable(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbService.closeResultSet(resultSet);
            DbService.closeStatement(statement);
            DbService.closeConnection(connection);
        }

        return timetable;
    }

    @Override
    public Timetable getById(int id) {

        Timetable timetable = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(TimetableQueries.GET_BY_ID);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                timetable = DbMapper.toTimetable(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbService.closeResultSet(resultSet);
            DbService.closeStatement(statement);
            DbService.closeConnection(connection);
        }

        return timetable;
    }

    @Override
    public boolean create(Timetable timetable, int doctorId) {

        Connection connection = null;
        PreparedStatement saveTimetableStatement = null;
        Statement getNewTimetableIdStatement = null;
        PreparedStatement createDoctorTimetableLinkStatement = null;
        ResultSet resultSet = null;

        try {

            connection = dbConnector.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);

            saveTimetableStatement = connection.prepareStatement(TimetableQueries.CREATE);
            getNewTimetableIdStatement = connection.createStatement();
            createDoctorTimetableLinkStatement = connection.prepareStatement(DoctorTimetableQueries.CREATE);

            saveTimetableStatement.setDate(1, DbMapper.mapToSqlDate(timetable.getDayOfAdmission()));
            saveTimetableStatement.setTime(2, DbMapper.mapTimeToSql(timetable.getStartTime()));
            saveTimetableStatement.setTime(3, DbMapper.mapTimeToSql(timetable.getEndTime()));

            int saveResult = saveTimetableStatement.executeUpdate();

            if (saveResult != 1) {
                connection.rollback();
                return false;
            } else {

                resultSet = getNewTimetableIdStatement.executeQuery(TimetableQueries.GET_NEW_TIMETABLE_ID);

                if (!resultSet.next()) {
                    connection.rollback();
                    return false;
                } else {

                    int timetableId = resultSet.getInt(1);

                    createDoctorTimetableLinkStatement.setInt(1, timetableId);
                    createDoctorTimetableLinkStatement.setInt(2, doctorId);

                    saveResult = createDoctorTimetableLinkStatement.executeUpdate();

                    if (saveResult != 1) {
                        connection.rollback();
                        return false;
                    } else {
                        connection.commit();
                        return true;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbService.closeResultSet(resultSet);
            DbService.closeStatement(saveTimetableStatement);
            DbService.closeStatement(getNewTimetableIdStatement);
            DbService.closeStatement(createDoctorTimetableLinkStatement);
            DbService.closeConnection(connection);
        }

        return false;
    }

    @Override
    public boolean update(Timetable obj) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(TimetableQueries.UPDATE);

            statement.setDate(1, DbMapper.mapToSqlDate(obj.getDayOfAdmission()));
            statement.setTime(2, DbMapper.mapTimeToSql(obj.getStartTime()));
            statement.setTime(3, DbMapper.mapTimeToSql(obj.getEndTime()));
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
    public boolean delete(Timetable obj) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(TimetableQueries.DELETE);
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
