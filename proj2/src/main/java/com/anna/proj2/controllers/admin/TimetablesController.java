package com.anna.proj2.controllers.admin;

import com.anna.proj2.controllers.util.Errors;
import com.anna.proj2.controllers.util.TimetableParser;
import com.anna.proj2.controllers.util.TimetableUi;
import com.anna.proj2.exceptions.UnableToDeleteException;
import com.anna.proj2.exceptions.UnableToSaveException;
import com.anna.proj2.exceptions.UnableToUpdateException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.interactors.admin.TimetablesAdminInteractor;
import com.anna.proj2.pojo.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/timetables")
public class TimetablesController {

    @Autowired
    private TimetablesAdminInteractor interactor;

    @GetMapping("/all/{doctorId}")
    public String allByDoctorId(
            @PathVariable("doctorId") int doctorId,
            Model model
    ) {

        List<TimetableUi> timetables = interactor
                .getTimetablesByDoctorId(doctorId)
                .stream()
                .map(TimetableParser::toTimetableUi)
                .collect(Collectors.toList());

        model.addAttribute("timetables", timetables);
        model.addAttribute("doctorId", doctorId);

        return "admin-timetables";
    }

    @GetMapping("/add/{doctorId}")
    public String addPage() {
        return "add-edit-timetable";
    }

    @PostMapping("/add/{doctorId}")
    public String add(
            @RequestParam("dayOfAdmission") String dayOfAdmission,
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime,
            @PathVariable("doctorId") int doctorId,
            Model model
    ) {

        try {
            Timetable timetable = TimetableParser.toTimetable(
                    new TimetableUi(0, dayOfAdmission, startTime, endTime)
            );
            interactor.saveTimetable(timetable, doctorId);

            return "redirect:/admin/timetables/all/" + doctorId;

        } catch (ValidationException e) {
            model.addAttribute("message", Errors.INVALID_DATA);
            return "error";

        } catch (UnableToSaveException e) {
            model.addAttribute("message", Errors.UNABLE_TO_SAVE);
            return "error";
        }
    }

    @GetMapping("/update/{doctorId}/{timetableId}")
    public String updatePage() {
        return "add-edit-timetable";
    }

    @PostMapping("/update/{doctorId}/{timetableId}")
    public String update(
            @PathVariable("timetableId") int timetableId,
            @RequestParam("dayOfAdmission") String dayOfAdmission,
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime,
            @PathVariable("doctorId") int doctorId,
            Model model
    ) {

        try {
            Timetable timetable = TimetableParser.toTimetable(
                    new TimetableUi(timetableId, dayOfAdmission, startTime, endTime)
            );
            interactor.updateTimetable(timetable);

            return "redirect:/admin/timetables/all/" + doctorId;

        } catch (ValidationException e) {
            model.addAttribute("message", Errors.INVALID_DATA);
            return "error";

        } catch (UnableToUpdateException e) {
            model.addAttribute("message", Errors.UNABLE_TO_UPDATE);
            return "error";
        }
    }

    @GetMapping("/delete/{doctorId}/{timetableId}/{dayOfAdmission}/{startTime}/{endTime}")
    public String delete(
            @PathVariable("timetableId") int timetableId,
            @PathVariable("dayOfAdmission") String dayOfAdmission,
            @PathVariable("startTime") String startTime,
            @PathVariable("endTime") String endTime,
            @PathVariable("doctorId") int doctorId,
            Model model
    ) {

        try {
            Timetable timetable = TimetableParser.toTimetable(
                    new TimetableUi(timetableId, dayOfAdmission, startTime, endTime)
            );
            interactor.deleteTimetable(timetable);

            return "redirect:/admin/timetables/all/" + doctorId;

        } catch (UnableToDeleteException e) {
            model.addAttribute("message", Errors.UNABLE_TO_DELETE);
            return "error";
        }
    }
}
