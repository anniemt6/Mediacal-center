package com.anna.proj2.data.dao;

import java.util.List;

public interface DoctorTimetableDao {

    List<Integer> getTimeTableIdsByDoctorId(int id);
}
