package com.anna.proj2.interactors.admin;

import com.anna.proj2.exceptions.UnableToDeleteException;
import com.anna.proj2.exceptions.UnableToSaveException;
import com.anna.proj2.exceptions.UnableToUpdateException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.pojo.DoctorOffice;

import java.util.List;

public interface DoctorOfficesInteractor {

    List<DoctorOffice> getAllDoctorOffices();

    void saveDoctorOffice(DoctorOffice doctorOffice) throws ValidationException, UnableToSaveException;

    void updateDoctorOffice(DoctorOffice doctorOffice) throws ValidationException, UnableToUpdateException;

    void deleteOffice(DoctorOffice doctorOffice) throws UnableToDeleteException;
}
