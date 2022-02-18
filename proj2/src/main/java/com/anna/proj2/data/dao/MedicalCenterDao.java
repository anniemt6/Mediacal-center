package com.anna.proj2.data.dao;

import com.anna.proj2.pojo.MedicalCenter;

public interface MedicalCenterDao {

    MedicalCenter getMedicalCenter();

    boolean update(MedicalCenter medicalCenter);
}
