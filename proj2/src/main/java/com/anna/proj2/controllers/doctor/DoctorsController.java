package com.anna.proj2.controllers.doctor;

import com.anna.proj2.controllers.util.*;
import com.anna.proj2.exceptions.NotExistsException;
import com.anna.proj2.exceptions.UnableToUpdateException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.interactors.patient.DoctorsInteractor;
import com.anna.proj2.interactors.patient.PatientsInteractor;
import com.anna.proj2.interactors.patient.TimetablesInteractor;
import com.anna.proj2.pojo.Doctor;
import com.anna.proj2.pojo.Patient;
import com.anna.proj2.pojo.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/doctors")
public class DoctorsController {

    @Autowired
    @Qualifier("doctorsInteractor")
    private DoctorsInteractor doctorsInteractor;

    @Autowired
    @Qualifier("timetablesInteractor")
    private TimetablesInteractor timetablesInteractor;

    @Autowired
    private PatientsInteractor patientsInteractor;

    @GetMapping("/{id}/timetables")
    public String getDoctorTimetables(
            @PathVariable("id") int id,
            Model model
    ) {

        List<TimetableUi> timetables = timetablesInteractor
                .getTimetablesByDoctorId(id)
                .stream()
                .map(TimetableParser::toTimetableUi)
                .collect(Collectors.toList());

        Doctor doctor = doctorsInteractor
                .getAllDoctors()
                .stream()
                .filter(d -> d.getId() == id)
                .collect(Collectors.toList())
                .get(0);

        model.addAttribute("timetables", timetables);
        model.addAttribute("doctorId", id);
        model.addAttribute("firstName", doctor.getFirstName());
        model.addAttribute("lastName", doctor.getLastName());
        model.addAttribute("middleName", doctor.getMiddleName());

        return "doctor-timetables";
    }

    @GetMapping("/{dId}/{tId}/requests")
    public String getRequests(
            @PathVariable("dId") int doctorId,
            @PathVariable("tId") int timetableId,
            Model model
    ) {

        List<RequestUi> requests = doctorsInteractor
                .getDoctorRequestsByTimetableId(doctorId, timetableId)
                .stream()
                .map(RequestParser::toRequestUi)
                .collect(Collectors.toList());

        try {

            for (RequestUi request : requests) {
                Patient patient = patientsInteractor.getPatientById(request.getPatientId());
                request.setPatientName(patient.getFirstName() + " " + patient.getLastName() + " " + patient.getMiddleName());
            }

        } catch (NotExistsException e) {

            model.addAttribute("message", Errors.NOT_EXISTS);
            return "error";
        }

        model.addAttribute("requests", requests);

        return "doctor-account-patient-visit";
    }

    @GetMapping("/requests/present/{id}/{pId}/{tId}/{dId}/{time}")
    public String markRequestAsPresent(
            @PathVariable("id") int id,
            @PathVariable("pId") int patientId,
            @PathVariable("tId") int timetableId,
            @PathVariable("dId") int doctorId,
            @PathVariable("time") String visitTime,
            Model model
    ) {

        Request request = RequestParser.toRequest(new RequestUi(
                id, patientId, timetableId, doctorId,
                true, visitTime
        ));

        try {

            doctorsInteractor.updateRequest(request);
        } catch (ValidationException e) {

            model.addAttribute("message", Errors.INVALID_DATA);
            return "error";
        } catch (UnableToUpdateException e) {

            model.addAttribute("message", Errors.UNABLE_TO_UPDATE);
            return "error";
        }

        return "redirect:/doctors/" + doctorId + "/" + timetableId + "/requests";
    }

    @GetMapping("/requests/absent/{id}/{pId}/{tId}/{dId}/{time}")
    public String markRequestAsAbsent(
            @PathVariable("id") int id,
            @PathVariable("pId") int patientId,
            @PathVariable("tId") int timetableId,
            @PathVariable("dId") int doctorId,
            @PathVariable("time") String visitTime,
            Model model
    ) {

        Request request = RequestParser.toRequest(new RequestUi(
                id, patientId, timetableId, doctorId,
                false, visitTime
        ));

        try {

            doctorsInteractor.updateRequest(request);
        } catch (ValidationException e) {

            model.addAttribute("message", Errors.INVALID_DATA);
            return "error";
        } catch (UnableToUpdateException e) {

            model.addAttribute("message", Errors.UNABLE_TO_UPDATE);
            return "error";
        }

        return "redirect:/doctors/" + doctorId + "/" + timetableId + "/requests";
    }
}
