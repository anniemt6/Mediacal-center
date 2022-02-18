package com.anna.proj2.data.dao;

import com.anna.proj2.pojo.DoctorOffice;

import java.util.List;

public interface DoctorOfficeDao extends BasicDao<DoctorOffice> {

    List<DoctorOffice> getAll();
}
