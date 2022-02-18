package com.anna.proj2.controllers.main;

import com.anna.proj2.controllers.util.DateTimeParser;
import com.anna.proj2.controllers.util.Errors;
import com.anna.proj2.controllers.util.TimetableParser;
import com.anna.proj2.controllers.util.TimetableUi;
import com.anna.proj2.exceptions.NotExistsException;
import com.anna.proj2.exceptions.UnableToSaveException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.interactors.patient.DoctorsInteractor;
import com.anna.proj2.interactors.patient.MedicalCenterInfoInteractor;
import com.anna.proj2.interactors.patient.PatientsInteractor;
import com.anna.proj2.interactors.patient.TimetablesInteractor;
import com.anna.proj2.pojo.Doctor;
import com.anna.proj2.pojo.MedicalCenter;
import com.anna.proj2.pojo.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    private DoctorsInteractor doctorsInteractor;

    @Autowired
    private PatientsInteractor patientsInteractor;

    @Autowired
    private TimetablesInteractor timetablesInteractor;

    @Autowired
    @Qualifier("patientsMedicalCenterInfoInteractor")
    private MedicalCenterInfoInteractor medicalCenterInfoInteractor;

    @GetMapping("/")
    public String main(@RequestParam("patient") String patient, Model model) {
        model.addAttribute("title", "Главная страница");
        model.addAttribute("patient", patient);
        return "home-page";
    }

    @GetMapping("/bestApp/{patientId}")
    public String home(@PathVariable("patientId") String patientId) {
        return "redirect:/?patient=" + patientId;
    }

    @GetMapping("/doctors/{patient}/all")
    public String all(@PathVariable("patient") String patient, Model model) {

        List<Doctor> doctors = doctorsInteractor.getAllDoctors();
        model.addAttribute("doctors", doctors);
        model.addAttribute("patient", patient);

        return "all-doctors";
    }

    @GetMapping("/doctors/{patient}/all/byNameAndSurname")
    public String allByNameAndSurname(
            @RequestParam("nameSurname") String nameSurname,
            @PathVariable("patient") String patient,
            Model model
    ) {

        if (nameSurname.isEmpty()) {

            return "redirect:/doctors/" + patient + "/all";
        } else {

            String[] nameAndSurname = nameSurname.split(" ");

            if (nameAndSurname.length != 2) {
                return "redirect:/doctors/" + patient + "/all";
            }

            List<Doctor> doctors = doctorsInteractor.getDoctorByNameAndSurname(nameAndSurname[0], nameAndSurname[1]);
            model.addAttribute("doctors", doctors);
            model.addAttribute("patient", patient);

            return "all-doctors";
        }
    }

    @GetMapping("/medicalCenter/info/{patient}")
    public String getMedicalCenterInfo(
            @PathVariable("patient") String patient,
            Model model
    ) {

        try {

            MedicalCenter medicalCenter = medicalCenterInfoInteractor.getMedicalCenterInfo();
            model.addAttribute("patient", patient);
            model.addAttribute("medicalCenter", medicalCenter);
            return "medcenter-info";
        } catch (NotExistsException e) {

            model.addAttribute("message", Errors.NOT_EXISTS);
            return "error";
        }
    }

    @GetMapping("/doctor/{dId}/{patient}/timetables")
    public String getDoctorTimetables(
            @PathVariable("dId") int doctorId,
            @PathVariable("patient") String patient,
            Model model
    ) {

        List<TimetableUi> timetables = timetablesInteractor
                .getTimetablesByDoctorId(doctorId)
                .stream()
                .map(TimetableParser::toTimetableUi)
                .collect(Collectors.toList());

        List<List<String>> times = new LinkedList();
        for (TimetableUi timetable : timetables) {
            times.add(patientsInteractor.getAvailableRequestTime(
                    TimetableParser.toTimetable(timetable),
                    doctorId
            ).stream().map(DateTimeParser::timeToString).collect(Collectors.toList()));
        }

        model.addAttribute("timetables", timetables);
        model.addAttribute("times", times);
        model.addAttribute("patient", patient);
        model.addAttribute("doctorId", doctorId);

        return "patient-doctor-timetables";
    }

    @GetMapping("/doctor/{dId}/{patient}/timetables/{tId}/{date}/{startTime}/{endTime}/request/{time}")
    public String makeRequest(
            @PathVariable("dId") int doctorId,
            @PathVariable("patient") String patient,
            @PathVariable("tId") int timetableId,
            @PathVariable("date") String dayOfAdmission,
            @PathVariable("startTime") String startTime,
            @PathVariable("endTime") String endTime,
            @PathVariable("time") String time,
            Model model
    ) {

        try {

            int patientId = Integer.parseInt(patient);

            patientsInteractor.saveRequest(new Request(
                    0,
                    false,
                    DateTimeParser.stringToTimeIfFailureReturnNull(time),
                    patientId, timetableId, doctorId
            ));

            return "redirect:/patient/" + patientId;

        } catch (NumberFormatException e) {

            return "redirect:/login/patient/" + doctorId + "/timetables/" + timetableId + "/" +
                    dayOfAdmission + "/" + startTime + "/" + endTime;
        } catch (UnableToSaveException e) {

            model.addAttribute("message", Errors.UNABLE_TO_SAVE);
            return "error";
        } catch (ValidationException e) {

            model.addAttribute("message", Errors.INVALID_DATA);
            return "error";
        }
    }
}