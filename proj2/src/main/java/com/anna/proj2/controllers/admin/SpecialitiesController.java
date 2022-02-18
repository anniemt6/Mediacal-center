package com.anna.proj2.controllers.admin;

import com.anna.proj2.controllers.util.Errors;
import com.anna.proj2.exceptions.UnableToDeleteException;
import com.anna.proj2.exceptions.UnableToSaveException;
import com.anna.proj2.exceptions.UnableToUpdateException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.interactors.admin.SpecialitiesInteractor;
import com.anna.proj2.pojo.Speciality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/specialities")
public class SpecialitiesController {

    @Autowired
    private SpecialitiesInteractor interactor;

    @GetMapping("/all")
    public String all(Model model) {

        List<Speciality> specialities = interactor.getAllSpecialities();
        model.addAttribute("specialities", specialities);

        return "specialities";
    }

    @GetMapping("/add")
    public String addPage() {
        return "add-speciality";
    }

    @PostMapping("/add")
    public String add(
            @RequestParam("speciality") String speciality,
            Model model
    ) {

        try {
            interactor.saveSpeciality(new Speciality(0, speciality));
            return "redirect:/admin/specialities/all";

        } catch (ValidationException e) {
            model.addAttribute("message", Errors.INVALID_DATA);
            return "error";

        } catch (UnableToSaveException e) {
            model.addAttribute("message", Errors.UNABLE_TO_SAVE);
            return "error";
        }
    }

    @GetMapping("/delete/{id}/{speciality}")
    public String delete(
            @PathVariable("id") int id,
            @PathVariable("speciality") String speciality,
            Model model
    ) {

        try {
            interactor.deleteSpeciality(new Speciality(id, speciality));
            return "redirect:/admin/specialities/all";

        } catch (UnableToDeleteException e) {
            model.addAttribute("message", Errors.UNABLE_TO_DELETE);
            return "error";
        }
    }
}
