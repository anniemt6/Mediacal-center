package com.anna.proj2.controllers.admin;

import com.anna.proj2.controllers.util.Errors;
import com.anna.proj2.exceptions.NotExistsException;
import com.anna.proj2.exceptions.UnableToUpdateException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.interactors.admin.MedicalCenterInteractor;
import com.anna.proj2.pojo.MedicalCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/medicalCenter")
public class MedicalCenterController {

    @Autowired
    private MedicalCenterInteractor interactor;

    @GetMapping("/info")
    public String getInfo(Model model) {

        try {

            MedicalCenter medicalCenter = interactor.getMedicalCenterInfo();
            model.addAttribute("medicalCenter", medicalCenter);

            return "admin-medcenter";

        } catch (NotExistsException e) {
            model.addAttribute("message", Errors.NOT_EXISTS);
            return "error";
        }
    }

    @GetMapping("/update/{id}")
    public String updatePage() {
        return "edit-medcenter";
    }

    @PostMapping("/update/{id}")
    public String update(
            @PathVariable("id") int id,
            @RequestParam("labName") String labName,
            @RequestParam("email") String email,
            @RequestParam("phoneNum") String phoneNum,
            @RequestParam("address") String address,
            Model model
    ) {

        try {
            interactor.updateMedicalCenter(new MedicalCenter(
                    id, labName, email, phoneNum, address
            ));
            return "redirect:/admin/medicalCenter/info";

        } catch (ValidationException e) {
            model.addAttribute("message", Errors.INVALID_DATA);
            return "error";

        } catch (UnableToUpdateException e) {
            model.addAttribute("message", Errors.UNABLE_TO_UPDATE);
            return "error";
        }
    }
}
