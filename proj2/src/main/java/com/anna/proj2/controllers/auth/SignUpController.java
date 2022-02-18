package com.anna.proj2.controllers.auth;

import com.anna.proj2.controllers.util.DateTimeParser;
import com.anna.proj2.controllers.util.Errors;
import com.anna.proj2.exceptions.UnableToSignupPatientException;
import com.anna.proj2.exceptions.UserNotExistsException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.interactors.auth.LoginInteractor;
import com.anna.proj2.interactors.auth.SignupInteractor;
import com.anna.proj2.pojo.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    private SignupInteractor interactor;

    @Autowired
    private LoginInteractor loginInteractor;

    @PostMapping("/patient")
    public String signUpPatient(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("middleName") String middleName,
            @RequestParam("phoneNum") String phoneNum,
            @RequestParam("dateOfBirth") String dateOfBirth,
            @RequestParam("address") String address,
            @RequestParam("sex") String sex,
            @RequestParam("medicalHistoryNum") int medicalHistoryNum,
            @RequestParam("patientPassword") String patientPassword,
            @RequestParam("patientEmail") String patientEmail,
            Model model
    ) {

        try {

            interactor.signupPatient(new Patient(
                    0,
                    firstName, lastName, middleName,
                    phoneNum,
                    DateTimeParser.stringToDateIfFailureReturnNull(dateOfBirth),
                    address, sex, patientPassword, patientEmail, medicalHistoryNum
            ));

            Patient patient = loginInteractor.loginPatient(patientEmail, patientPassword);

            return "redirect:/patient/" + patient.getId();
        } catch (ValidationException e) {

            model.addAttribute("message", Errors.INVALID_DATA);
            return "error";
        } catch (UnableToSignupPatientException e) {

            model.addAttribute("message", Errors.UNABLE_TO_SIGNUP);
            return "error";
        } catch (UserNotExistsException e) {

            model.addAttribute("message", Errors.UNABLE_TO_SIGNUP);
            return "error";
        }
    }

    @PostMapping("/patient/{dId}/timetables/{tId}/{date}/{startTime}/{endTime}")
    public String signUpForRequest(
            @PathVariable("dId") int doctorId,
            @PathVariable("tId") int timetableId,
            @PathVariable("date") String dayOfAdmission,
            @PathVariable("startTime") String startTime,
            @PathVariable("endTime") String endTime,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("middleName") String middleName,
            @RequestParam("phoneNum") String phoneNum,
            @RequestParam("dateOfBirth") String dateOfBirth,
            @RequestParam("address") String address,
            @RequestParam("sex") String sex,
            @RequestParam("medicalHistoryNum") int medicalHistoryNum,
            @RequestParam("patientPassword") String patientPassword,
            @RequestParam("patientEmail") String patientEmail,
            Model model
    ) {

        try {

            model.addAttribute("doctorId", doctorId);
            model.addAttribute("timetableId", timetableId);
            model.addAttribute("dayOfAdmission", dayOfAdmission);
            model.addAttribute("startTime", startTime);
            model.addAttribute("endTime", endTime);

            interactor.signupPatient(new Patient(
                    0,
                    firstName, lastName, middleName,
                    phoneNum,
                    DateTimeParser.stringToDateIfFailureReturnNull(dateOfBirth),
                    address, sex, patientPassword, patientEmail, medicalHistoryNum
            ));

            Patient patient = loginInteractor.loginPatient(patientEmail, patientPassword);

            return "redirect:/doctor/" + doctorId + "/" + patient.getId() + "/timetables";
        } catch (ValidationException e) {

            model.addAttribute("message", Errors.INVALID_DATA);
            return "error";
        } catch (UnableToSignupPatientException e) {

            model.addAttribute("message", Errors.UNABLE_TO_SIGNUP);
            return "error";
        } catch (UserNotExistsException e) {

            model.addAttribute("message", Errors.UNABLE_TO_SIGNUP);
            return "error";
        }
    }
}
