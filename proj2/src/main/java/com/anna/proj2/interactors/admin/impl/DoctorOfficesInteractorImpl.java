package com.anna.proj2.interactors.admin.impl;

import com.anna.proj2.data.repository.AdminRepository;
import com.anna.proj2.exceptions.UnableToDeleteException;
import com.anna.proj2.exceptions.UnableToSaveException;
import com.anna.proj2.exceptions.UnableToUpdateException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.interactors.admin.DoctorOfficesInteractor;
import com.anna.proj2.pojo.DoctorOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorOfficesInteractorImpl implements DoctorOfficesInteractor {

    @Autowired
    private AdminRepository repository;

    @Override
    public List<DoctorOffice> getAllDoctorOffices() {
        return repository.getAllDoctorOffices();
    }

    @Override
    public void saveDoctorOffice(DoctorOffice doctorOffice) throws ValidationException, UnableToSaveException {

        if (!validate(doctorOffice)) {
            throw new ValidationException();
        }

        if (!repository.saveDoctorOffice(doctorOffice)) {
            throw new UnableToSaveException();
        }
    }

    @Override
    public void updateDoctorOffice(DoctorOffice doctorOffice) throws ValidationException, UnableToUpdateException {

        if (!validate(doctorOffice)) {
            throw new ValidationException();
        }

        if (!repository.updateDoctorOffice(doctorOffice)) {
            throw new UnableToUpdateException();
        }
    }

    @Override
    public void deleteOffice(DoctorOffice doctorOffice) throws UnableToDeleteException {

        if (!repository.deleteDoctorOffice(doctorOffice)) {
            throw new UnableToDeleteException();
        }
    }

    private boolean validate(DoctorOffice office) {
        return !office.getOfficeNum().isEmpty();
    }
}
