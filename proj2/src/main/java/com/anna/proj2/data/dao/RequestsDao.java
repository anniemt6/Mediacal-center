package com.anna.proj2.data.dao;

import com.anna.proj2.pojo.Request;
import java.util.List;

public interface RequestsDao extends BasicDao<Request> {

    List<Request> getByDoctorId(int id);

    List<Request> getByPatientId(int id);

    void cancelRequest(int requestId);
}
