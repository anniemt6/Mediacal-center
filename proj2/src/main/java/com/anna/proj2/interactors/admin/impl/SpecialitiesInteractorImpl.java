package com.anna.proj2.interactors.admin.impl;

import com.anna.proj2.data.repository.AdminRepository;
import com.anna.proj2.exceptions.UnableToDeleteException;
import com.anna.proj2.exceptions.UnableToSaveException;
import com.anna.proj2.exceptions.UnableToUpdateException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.interactors.admin.SpecialitiesInteractor;
import com.anna.proj2.pojo.Speciality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialitiesInteractorImpl implements SpecialitiesInteractor {

    @Autowired
    private AdminRepository repository;

    @Override
    public List<Speciality> getAllSpecialities() {
        return repository.getAllSpecialities();
    }

    @Override
    public void saveSpeciality(Speciality speciality) throws ValidationException, UnableToSaveException {

        if (!validate(speciality)) {
            throw new ValidationException();
        }

        if (!repository.saveSpeciality(speciality)) {
            throw new UnableToSaveException();
        }
    }

    @Override
    public void updateSpeciality(Speciality speciality) throws ValidationException, UnableToUpdateException {

        if (!validate(speciality)) {
            throw new ValidationException();
        }

        if (!repository.updateSpeciality(speciality)) {
            throw new UnableToUpdateException();
        }
    }

    @Override
    public void deleteSpeciality(Speciality speciality) throws UnableToDeleteException {

        if (!repository.deleteSpeciality(speciality)) {
            throw new UnableToDeleteException();
        }
    }

    private boolean validate(Speciality speciality) {
        return !speciality.getSpeciality().isEmpty();
    }
}
