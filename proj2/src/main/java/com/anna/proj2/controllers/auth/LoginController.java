package com.anna.proj2.controllers.auth;

import com.anna.proj2.controllers.util.Errors;
import com.anna.proj2.exceptions.UserNotExistsException;
import com.anna.proj2.interactors.auth.LoginInteractor;
import com.anna.proj2.pojo.Doctor;
import com.anna.proj2.pojo.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginInteractor interactor;

    @GetMapping("/doctor")
    public String loginDoctorPage() {
        return "login";
    }

    @PostMapping("/doctor")
    public String loginDoctor(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model
    ) {

        try {

            Doctor doctor = interactor.loginDoctor(email, password);
            return "redirect:/doctors/" + doctor.getId() + "/timetables";
        } catch (UserNotExistsException e) {

            model.addAttribute("message", Errors.INVALID_SIGNUP);
            return "error";
        }
    }

    @GetMapping("/patient")
    public String loginPatientPage() {
        return "login-signup";
    }

    @PostMapping("/patient")
    public String loginPatient(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model
    ) {

        try {

            Patient patient = interactor.loginPatient(email, password);
            return "redirect:/patient/" + patient.getId();
        } catch (UserNotExistsException e) {

            model.addAttribute("message", Errors.INVALID_SIGNUP);
            return "error";
        }
    }

    @GetMapping("/patient/{dId}/timetables/{tId}/{date}/{startTime}/{endTime}")
    public String loginForRequestPage(
            @PathVariable("dId") int doctorId,
            @PathVariable("tId") int timetableId,
            @PathVariable("date") String dayOfAdmission,
            @PathVariable("startTime") String startTime,
            @PathVariable("endTime") String endTime,
            Model model
    ) {

        model.addAttribute("doctorId", doctorId);
        model.addAttribute("timetableId", timetableId);
        model.addAttribute("dayOfAdmission", dayOfAdmission);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);

        return "login-signup-for-request";
    }

    @PostMapping("/patient/{dId}/timetables/{tId}/{date}/{startTime}/{endTime}")
    public String loginForRequest(
            @PathVariable("dId") int doctorId,
            @PathVariable("tId") int timetableId,
            @PathVariable("date") String dayOfAdmission,
            @PathVariable("startTime") String startTime,
            @PathVariable("endTime") String endTime,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model
    ) {

        try {

            model.addAttribute("doctorId", doctorId);
            model.addAttribute("timetableId", timetableId);
            model.addAttribute("dayOfAdmission", dayOfAdmission);
            model.addAttribute("startTime", startTime);
            model.addAttribute("endTime", endTime);

            Patient patient = interactor.loginPatient(email, password);
            return "redirect:/doctor/" + doctorId + "/" + patient.getId() + "/timetables/" +
                    timetableId + "/" + dayOfAdmission + "/" + startTime + "/" + endTime;
        } catch (UserNotExistsException e) {

            model.addAttribute("message", Errors.INVALID_SIGNUP);
            return "error";
        }
    }
}
