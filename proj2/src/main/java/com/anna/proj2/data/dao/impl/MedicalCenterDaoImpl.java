package com.anna.proj2.data.dao.impl;

import com.anna.proj2.data.dao.MedicalCenterDao;
import com.anna.proj2.data.utils.DbConnector;
import com.anna.proj2.data.utils.DbService;
import com.anna.proj2.data.utils.mapping.DbMapper;
import com.anna.proj2.data.utils.queries.MedicalCenterQueries;
import com.anna.proj2.pojo.MedicalCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class MedicalCenterDaoImpl implements MedicalCenterDao {

    @Autowired
    private DbConnector dbConnector;

    @Override
    public MedicalCenter getMedicalCenter() {

        MedicalCenter medicalCenter = null;

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery(MedicalCenterQueries.GET_MEDICAL_CENTER_DATA);

            if (resultSet.next()) {
                medicalCenter = DbMapper.toMedicalCenter(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbService.closeResultSet(resultSet);
            DbService.closeStatement(statement);
            DbService.closeConnection(connection);
        }

        return medicalCenter;
    }

    @Override
    public boolean update(MedicalCenter medicalCenter) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(MedicalCenterQueries.UPDATE_MEDICAL_CENTER_DATA);

            statement.setString(1, medicalCenter.getLabName());
            statement.setString(2, medicalCenter.getEMail());
            statement.setString(3, medicalCenter.getPhoneNum());
            statement.setString(4, medicalCenter.getAddress());
            statement.setInt(5, medicalCenter.getId());

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
