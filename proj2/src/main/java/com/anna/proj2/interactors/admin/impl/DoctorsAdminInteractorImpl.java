package com.anna.proj2.interactors.admin.impl;

import com.anna.proj2.data.repository.AdminRepository;
import com.anna.proj2.exceptions.UnableToDeleteException;
import com.anna.proj2.exceptions.UnableToUpdateException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.interactors.admin.DoctorsAdminInteractor;
import com.anna.proj2.interactors.patient.impl.DoctorsInteractorImpl;
import com.anna.proj2.pojo.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorsAdminInteractorImpl extends DoctorsInteractorImpl implements DoctorsAdminInteractor {

    @Autowired
    private AdminRepository repository;

    @Override
    public int getVisitToDoctorAmount(int doctorId) {
        return repository.getVisitToDoctorAmount(doctorId);
    }

    @Override
    public void updateDoctor(Doctor doctor) throws ValidationException, UnableToUpdateException {

        if (!validate(doctor)) {
            throw new ValidationException();
        }

        if (!repository.updateDoctor(doctor)) {
            throw new UnableToUpdateException();
        }
    }

    @Override
    public void deleteDoctor(Doctor doctor) throws UnableToDeleteException {

        if (!repository.deleteDoctor(doctor)) {
            throw new UnableToDeleteException();
        }
    }

    private boolean validate(Doctor doctor) {
        return doctor.getFirstName().length() > 2 &&
                doctor.getLastName().length() > 2 &&
                doctor.getMiddleName().length() > 2 &&
                doctor.getDoctorEmail().matches("^(.+)@(.+)\\.(.+)$") &&
                doctor.getDoctorPassword().length() >= 8;
    }
}
