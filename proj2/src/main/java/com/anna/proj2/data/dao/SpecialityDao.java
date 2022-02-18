package com.anna.proj2.data.dao;

import com.anna.proj2.data.dao.BasicDao;
import com.anna.proj2.pojo.Speciality;

import java.util.List;

public interface SpecialityDao extends BasicDao<Speciality> {

    List<Speciality> getAll();
}
