package com.anna.proj2.interactors.patient;

import com.anna.proj2.exceptions.NotExistsException;
import com.anna.proj2.pojo.MedicalCenter;

public interface MedicalCenterInfoInteractor {

    MedicalCenter getMedicalCenterInfo() throws NotExistsException;
}
