package com.anna.proj2.interactors.patient;

import com.anna.proj2.exceptions.UnableToUpdateException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.pojo.Request;

public interface RequestsInteractor {

    void updateRequest(Request request) throws ValidationException, UnableToUpdateException;
}
