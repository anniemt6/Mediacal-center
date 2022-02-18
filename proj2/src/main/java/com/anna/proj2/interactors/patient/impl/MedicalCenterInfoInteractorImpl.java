package com.anna.proj2.interactors.patient.impl;

import com.anna.proj2.data.repository.MainRepository;
import com.anna.proj2.exceptions.NotExistsException;
import com.anna.proj2.interactors.patient.MedicalCenterInfoInteractor;
import com.anna.proj2.pojo.MedicalCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("patientsMedicalCenterInfoInteractor")
public class MedicalCenterInfoInteractorImpl implements MedicalCenterInfoInteractor {

    @Autowired
    private MainRepository repository;

    @Override
    public MedicalCenter getMedicalCenterInfo() throws NotExistsException {

        MedicalCenter medicalCenter = repository.getMedicalCenterInfo();

        if (medicalCenter != null) {
            return medicalCenter;
        } else {
            throw new NotExistsException();
        }
    }
}
