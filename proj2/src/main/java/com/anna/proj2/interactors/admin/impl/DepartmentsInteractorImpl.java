package com.anna.proj2.interactors.admin.impl;

import com.anna.proj2.data.repository.AdminRepository;
import com.anna.proj2.exceptions.UnableToDeleteException;
import com.anna.proj2.exceptions.UnableToSaveException;
import com.anna.proj2.exceptions.UnableToUpdateException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.interactors.admin.DepartmentsInteractor;
import com.anna.proj2.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentsInteractorImpl implements DepartmentsInteractor {

    @Autowired
    private AdminRepository repository;

    @Override
    public List<Department> getAllDepartments() {
        return repository.getAllDepartments();
    }

    @Override
    public void saveDepartment(Department department) throws ValidationException, UnableToSaveException {

        if (!validate(department)) {
            throw new ValidationException();
        }

        if (!repository.saveDepartment(department)) {
            throw new UnableToSaveException();
        }
    }

    @Override
    public void updateDepartment(Department department) throws ValidationException, UnableToUpdateException {

        if (!validate(department)) {
            throw new ValidationException();
        }

        if (!repository.updateDepartment(department)) {
            throw new UnableToUpdateException();
        }
    }

    @Override
    public void deleteDepartment(Department department) throws UnableToDeleteException {

        if (!repository.deleteDepartment(department)) {
            throw new UnableToDeleteException();
        }
    }

    private boolean validate(Department department) {
        return !department.getDepartmentName().isEmpty();
    }
}
