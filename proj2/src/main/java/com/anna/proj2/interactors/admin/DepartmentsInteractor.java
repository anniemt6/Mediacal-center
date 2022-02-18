package com.anna.proj2.interactors.admin;

import com.anna.proj2.exceptions.UnableToDeleteException;
import com.anna.proj2.exceptions.UnableToSaveException;
import com.anna.proj2.exceptions.UnableToUpdateException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.pojo.Department;

import java.util.List;

public interface DepartmentsInteractor {

    List<Department> getAllDepartments();

    void saveDepartment(Department department) throws ValidationException, UnableToSaveException;

    void updateDepartment(Department department) throws ValidationException, UnableToUpdateException;

    void deleteDepartment(Department department) throws UnableToDeleteException;
}
