package com.anna.proj2.controllers.patient;

import com.anna.proj2.controllers.util.*;
import com.anna.proj2.exceptions.NotExistsException;
import com.anna.proj2.exceptions.UnableToUpdateException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.interactors.patient.PatientsInteractor;
import com.anna.proj2.interactors.patient.TimetablesInteractor;
import com.anna.proj2.pojo.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/patient")
public class PatientProfileController {

    @Autowired
    private PatientsInteractor patientsInteractor;

    @Autowired
    private TimetablesInteractor timetablesInteractor;

    @GetMapping("/{id}")
    public String getProfile(@PathVariable("id") String stringId, Model model) {

        try {

            int id = Integer.parseInt(stringId);

            Patient patient = patientsInteractor.getPatientById(id);
            List<RequestUi> requests = patientsInteractor
                    .getPatientRequests(id)
                    .stream()
                    .map(RequestParser::toRequestUi)
                    .collect(Collectors.toList());

            List<TimetableUi> timetables = new LinkedList();
            for (RequestUi request : requests) {
                timetables.add(TimetableParser.toTimetableUi(
                        timetablesInteractor
                                .getTimetablesByDoctorId(request.getDoctorId())
                                .stream()
                                .filter(t -> t.getId() == request.getTimetableId())
                                .collect(Collectors.toList())
                                .get(0)
                ));
            }

            model.addAttribute("patient", patient);
            model.addAttribute("timetables", timetables);
            model.addAttribute("requests", requests);

            return "profile";
        } catch (NotExistsException e) {

            model.addAttribute("message", Errors.NOT_EXISTS);
            return "error";
        } catch (NumberFormatException e) {

            return "redirect:/login/patient";
        } catch (IndexOutOfBoundsException e) {

            model.addAttribute(Errors.NOT_EXISTS);
            return "error";
        }
    }

    @GetMapping("/{id}/update")
    public String updatePage(
            @PathVariable("id") int id,
            Model model
    ) {

        model.addAttribute("patient", id);
        return "edit-patient";
    }

    @PostMapping("/{id}/update")
    public String update(
            @PathVariable("id") int id,
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

            model.addAttribute("patient", id);
            patientsInteractor.updatePatient(new Patient(
                    id,
                    firstName, lastName, middleName,
                    phoneNum,
                    DateTimeParser.stringToDateIfFailureReturnNull(dateOfBirth),
                    address, sex, patientPassword, patientEmail, medicalHistoryNum
            ));

            return "redirect:/patient/" + id;
        } catch (ValidationException e) {

            model.addAttribute("message", Errors.INVALID_DATA);
            return "error";
        } catch (UnableToUpdateException e) {

            model.addAttribute("message", Errors.UNABLE_TO_UPDATE);
            return "error";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/bestApp/main";
    }

    @GetMapping("/{patientId}/cancelRequest/{requestId}")
    public String cancelRequest(
            @PathVariable("patientId") int patientId,
            @PathVariable("requestId") int id
    ) {

        patientsInteractor.cancelRequest(id);

        return "redirect:/patient/" + patientId;
    }
}
