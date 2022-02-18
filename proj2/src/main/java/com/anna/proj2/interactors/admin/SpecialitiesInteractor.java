package com.anna.proj2.interactors.admin;

import com.anna.proj2.exceptions.UnableToDeleteException;
import com.anna.proj2.exceptions.UnableToSaveException;
import com.anna.proj2.exceptions.UnableToUpdateException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.pojo.Speciality;

import java.util.List;

public interface SpecialitiesInteractor {

    List<Speciality> getAllSpecialities();

    void saveSpeciality(Speciality speciality) throws ValidationException, UnableToSaveException;

    void updateSpeciality(Speciality speciality) throws ValidationException, UnableToUpdateException;

    void deleteSpeciality(Speciality speciality) throws UnableToDeleteException;
}
