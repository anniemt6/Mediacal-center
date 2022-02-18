package com.anna.proj2.interactors.auth.impl;

import com.anna.proj2.data.repository.AdminRepository;
import com.anna.proj2.data.repository.MainRepository;
import com.anna.proj2.exceptions.UnableToSignupDoctorException;
import com.anna.proj2.exceptions.UnableToSignupPatientException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.interactors.auth.SignupInteractor;
import com.anna.proj2.pojo.Doctor;
import com.anna.proj2.pojo.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupInteractorImpl implements SignupInteractor {

    @Autowired
    private MainRepository repository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public void signupDoctor(Doctor doctor) throws ValidationException, UnableToSignupDoctorException {

        if (!validateDoctor(doctor)) {
            throw new ValidationException();
        }

        if (!adminRepository.saveDoctor(doctor)) {
            throw new UnableToSignupDoctorException();
        }
    }

    @Override
    public void signupPatient(Patient patient) throws ValidationException, UnableToSignupPatientException {

        if (!validatePatient(patient)) {
            throw new ValidationException();
        }

        if (!repository.savePatient(patient)) {
            throw new UnableToSignupPatientException();
        }
    }

    private boolean validateDoctor(Doctor doctor) {
        return doctor.getFirstName().length() > 2 &&
                doctor.getLastName().length() > 2 &&
                doctor.getMiddleName().length() > 2 &&
                doctor.getDoctorEmail().matches("^(.+)@(.+)\\.(.+)$") &&
                doctor.getDoctorPassword().length() >= 8;
    }

    private boolean validatePatient(Patient patient) {
        return patient.getFirstName().length() > 2 &&
                patient.getLastName().length() > 2 &&
                patient.getMiddleName().length() > 2 &&
                patient.getPatientEmail().matches("^(.+)@(.+)\\.(.+)$") &&
                patient.getPatientPassword().length() >= 8;
    }
}
