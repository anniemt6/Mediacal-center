package com.anna.proj2.interactors.admin.impl;

import com.anna.proj2.data.repository.AdminRepository;
import com.anna.proj2.exceptions.UnableToUpdateException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.interactors.admin.MedicalCenterInteractor;
import com.anna.proj2.interactors.patient.impl.MedicalCenterInfoInteractorImpl;
import com.anna.proj2.pojo.MedicalCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalCenterInteractorImpl extends MedicalCenterInfoInteractorImpl implements MedicalCenterInteractor {

    @Autowired
    private AdminRepository repository;

    @Override
    public void updateMedicalCenter(MedicalCenter medicalCenter) throws ValidationException, UnableToUpdateException {

        if (!validate(medicalCenter)) {
            throw new ValidationException();
        }

        if (!repository.updateMedicalCenter(medicalCenter)) {
            throw new UnableToUpdateException();
        }
    }

    private boolean validate(MedicalCenter medicalCenter) {
        return !medicalCenter.getAddress().isEmpty() &&
                !medicalCenter.getLabName().isEmpty() &&
                medicalCenter.getEMail().matches("^(.+)@(.+)\\.(.+)$") &&
                !medicalCenter.getPhoneNum().isEmpty();
    }
}
