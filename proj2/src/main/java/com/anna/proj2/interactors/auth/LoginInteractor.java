package com.anna.proj2.interactors.auth;

import com.anna.proj2.exceptions.UserNotExistsException;
import com.anna.proj2.pojo.Doctor;
import com.anna.proj2.pojo.Patient;

public interface LoginInteractor {

    Doctor loginDoctor(String email, String password) throws UserNotExistsException;

    Patient loginPatient(String email, String password) throws UserNotExistsException;
}
