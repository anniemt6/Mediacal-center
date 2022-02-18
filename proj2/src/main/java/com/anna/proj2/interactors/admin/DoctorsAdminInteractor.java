package com.anna.proj2.interactors.admin;

import com.anna.proj2.exceptions.UnableToDeleteException;
import com.anna.proj2.exceptions.UnableToUpdateException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.interactors.patient.DoctorsInteractor;
import com.anna.proj2.pojo.Doctor;

public interface DoctorsAdminInteractor extends DoctorsInteractor {

    int getVisitToDoctorAmount(int doctorId);

    void updateDoctor(Doctor doctor) throws ValidationException, UnableToUpdateException;

    void deleteDoctor(Doctor doctor) throws UnableToDeleteException;
}
