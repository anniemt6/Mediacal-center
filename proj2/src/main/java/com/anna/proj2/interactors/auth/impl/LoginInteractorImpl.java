package com.anna.proj2.interactors.auth.impl;

import com.anna.proj2.data.repository.MainRepository;
import com.anna.proj2.exceptions.UserNotExistsException;
import com.anna.proj2.interactors.auth.LoginInteractor;
import com.anna.proj2.pojo.Doctor;
import com.anna.proj2.pojo.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginInteractorImpl implements LoginInteractor {

    @Autowired
    private MainRepository repository;

    @Override
    public Doctor loginDoctor(String email, String password) throws UserNotExistsException {

        Doctor doctor = repository.getDoctorByEmailAndPassword(email, password);

        if (doctor != null) {
            return doctor;
        } else {
            throw new UserNotExistsException();
        }
    }

    @Override
    public Patient loginPatient(String email, String password) throws UserNotExistsException {

        Patient patient = repository.getPatientByEmailAndPassword(email, password);

        if (patient != null) {
            return patient;
        } else {
            throw new UserNotExistsException();
        }
    }
}
