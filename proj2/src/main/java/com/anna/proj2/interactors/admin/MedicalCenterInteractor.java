package com.anna.proj2.interactors.admin;

import com.anna.proj2.exceptions.UnableToUpdateException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.interactors.patient.MedicalCenterInfoInteractor;
import com.anna.proj2.pojo.MedicalCenter;

public interface MedicalCenterInteractor extends MedicalCenterInfoInteractor {

    void updateMedicalCenter(MedicalCenter medicalCenter) throws ValidationException, UnableToUpdateException;
}
