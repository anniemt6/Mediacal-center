package com.anna.proj2.controllers.admin;

import com.anna.proj2.controllers.util.Errors;
import com.anna.proj2.exceptions.UnableToDeleteException;
import com.anna.proj2.exceptions.UnableToSaveException;
import com.anna.proj2.exceptions.UnableToUpdateException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.interactors.admin.DepartmentsInteractor;
import com.anna.proj2.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/departments")
public class DepartmentsController {

    @Autowired
    private DepartmentsInteractor interactor;

    @GetMapping("/all")
    public String getAll(Model model) {

        List<Department> departments = interactor.getAllDepartments();
        model.addAttribute("departments", departments);

        return "departments";
    }

    @GetMapping("/add")
    public String addPage() {
        return "add-department";
    }

    @PostMapping("/add")
    public String add(
            @RequestParam("name") String departmentName,
            Model model
    ) {

        try {
            interactor.saveDepartment(new Department(0, departmentName));
            return "redirect:/admin/departments/all";

        } catch (ValidationException e) {
            model.addAttribute("message", Errors.INVALID_DATA);
            return "error";

        } catch (UnableToSaveException e) {
            model.addAttribute("message", Errors.UNABLE_TO_SAVE);
            return "error";
        }
    }

    @GetMapping("delete/{id}/{departmentName}")
    public String delete(
            @PathVariable("id") int id,
            @PathVariable("departmentName") String departmentName,
            Model model
    ) {

        try {

            interactor.deleteDepartment(new Department(id, departmentName));
            return "redirect:/admin/departments/all";

        } catch (UnableToDeleteException e) {
            model.addAttribute(Errors.UNABLE_TO_DELETE);
            return "error";
        }
    }
}
