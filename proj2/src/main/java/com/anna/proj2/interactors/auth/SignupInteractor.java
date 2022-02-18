package com.anna.proj2.interactors.auth;

import com.anna.proj2.exceptions.UnableToSignupDoctorException;
import com.anna.proj2.exceptions.UnableToSignupPatientException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.pojo.Doctor;
import com.anna.proj2.pojo.Patient;

public interface SignupInteractor {

    void signupDoctor(Doctor doctor) throws ValidationException, UnableToSignupDoctorException;

    void signupPatient(Patient patient) throws ValidationException, UnableToSignupPatientException;
}
