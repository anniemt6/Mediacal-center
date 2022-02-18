package com.anna.proj2.controllers.admin;

import com.anna.proj2.controllers.util.Errors;
import com.anna.proj2.exceptions.UnableToDeleteException;
import com.anna.proj2.exceptions.UnableToSaveException;
import com.anna.proj2.exceptions.UnableToUpdateException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.interactors.admin.DoctorOfficesInteractor;
import com.anna.proj2.pojo.DoctorOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/offices")
public class DoctorOfficesController {

    @Autowired
    private DoctorOfficesInteractor interactor;

    @GetMapping("/all")
    public String all(Model model) {

        List<DoctorOffice> offices = interactor.getAllDoctorOffices();
        model.addAttribute("offices", offices);

        return "offices";
    }

    @GetMapping("/add")
    public String addPage() {
        return "add-office";
    }

    @PostMapping("/add")
    public String add(
            @RequestParam("officeNum") String officeNum,
            Model model
    ) {

        try {
            interactor.saveDoctorOffice(new DoctorOffice(0, officeNum));
            return "redirect:/admin/offices/all";

        } catch (ValidationException e) {
            model.addAttribute("message", Errors.INVALID_DATA);
            return "error";

        } catch (UnableToSaveException e) {
            model.addAttribute(Errors.UNABLE_TO_SAVE);
            return "error";
        }
    }

    @GetMapping("/update/{id}")
    public String updatePage() {
        return "add-edit-office";
    }

    @PostMapping("/update/{id}")
    public String update(
            @PathVariable("id") int id,
            @RequestParam("officeNum") String officeNum,
            Model model
    ) {

        try {
            interactor.updateDoctorOffice(new DoctorOffice(id, officeNum));
            return "redirect:/admin/offices/all";

        } catch (ValidationException e) {
            model.addAttribute("message", Errors.INVALID_DATA);
            return "error";

        } catch (UnableToUpdateException e) {
            model.addAttribute("message", Errors.UNABLE_TO_UPDATE);
            return "error";
        }
    }

    @GetMapping("/delete/{id}/{officeNum}")
    public String delete(
            @PathVariable("id") int id,
            @PathVariable("officeNum") String officeNum,
            Model model
    ) {

        try {
            interactor.deleteOffice(new DoctorOffice(id, officeNum));
            return "redirect:/admin/offices/all";

        } catch (UnableToDeleteException e) {
            model.addAttribute("message", Errors.UNABLE_TO_DELETE);
            return "error";
        }
    }
}
