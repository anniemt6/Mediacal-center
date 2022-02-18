package com.anna.proj2.controllers.admin;

import com.anna.proj2.controllers.util.Errors;
import com.anna.proj2.exceptions.UnableToDeleteException;
import com.anna.proj2.exceptions.UnableToSignupDoctorException;
import com.anna.proj2.exceptions.UnableToUpdateException;
import com.anna.proj2.exceptions.ValidationException;
import com.anna.proj2.interactors.admin.DoctorsAdminInteractor;
import com.anna.proj2.interactors.auth.SignupInteractor;
import com.anna.proj2.pojo.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/admin/doctors")
public class DoctorsAdminController {

    @Autowired
    private DoctorsAdminInteractor doctorsInteractor;

    @Autowired
    private SignupInteractor signupInteractor;

    @GetMapping("/all")
    public String all(Model model) {

        List<Doctor> doctors = doctorsInteractor.getAllDoctors();
        List<Integer> visits = new LinkedList<>();
        doctors.forEach(doctor ->
                visits.add(doctorsInteractor.getVisitToDoctorAmount(doctor.getId()))
        );

        model.addAttribute("doctors", doctors);
        model.addAttribute("visits", visits);

        return "admin-all-doctors";
    }

    @GetMapping("/all/byNameAndSurname")
    public String allByNameAndSurname(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            Model model
    ) {

        List<Doctor> doctors = doctorsInteractor.getDoctorByNameAndSurname(name, surname);
        List<Integer> visits = new LinkedList<>();
        doctors.forEach(doctor ->
                visits.add(doctorsInteractor.getVisitToDoctorAmount(doctor.getId()))
        );

        model.addAttribute("doctors", doctors);
        model.addAttribute("visits", visits);

        return "admin-all-doctors";
    }

    @GetMapping("/all/bySpeciality")
    public String allBySpeciality(
            @RequestParam("speciality") String speciality,
            Model model
    ) {

        List<Doctor> doctors = doctorsInteractor.getDoctorBySpeciality(speciality);
        List<Integer> visits = new LinkedList<>();
        doctors.forEach(doctor ->
                visits.add(doctorsInteractor.getVisitToDoctorAmount(doctor.getId()))
        );

        model.addAttribute("doctors", doctors);
        model.addAttribute("visits", visits);

        return "admin-all-doctors";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register-edit-doctor";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("middleName") String middleName,
            @RequestParam("phoneNum") String phoneNum,
            @RequestParam("doctorPassword") String doctorPassword,
            @RequestParam("doctorEmail") String doctorEmail,
            @RequestParam("speciality") String speciality,
            @RequestParam("departmentName") String departmentName,
            @RequestParam("officeNumber") String officeNumber,
            Model model
    ) {

        try {
            signupInteractor.signupDoctor(new Doctor(
                    0,
                    firstName, lastName, middleName,
                    phoneNum,
                    doctorPassword, doctorEmail,
                    speciality, "ВераКлиник",
                    departmentName, officeNumber
            ));
            return "redirect:/admin/doctors/all";

        } catch (ValidationException e) {
            model.addAttribute("message", Errors.INVALID_DATA);
            return "error";

        } catch (UnableToSignupDoctorException e) {
            model.addAttribute("message", Errors.UNABLE_TO_SIGNUP);
            return "error";
        }
    }

    @GetMapping("/update/{id}")
    public String updatePage() {
        return "register-edit-doctor";
    }

    @PostMapping("/update/{id}")
    public String update(
            @PathVariable("id") int id,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("middleName") String middleName,
            @RequestParam("phoneNum") String phoneNum,
            @RequestParam("doctorPassword") String doctorPassword,
            @RequestParam("doctorEmail") String doctorEmail,
            @RequestParam("speciality") String speciality,
            @RequestParam("departmentName") String departmentName,
            @RequestParam("officeNumber") String officeNumber,
            Model model
    ) {

        try {
            doctorsInteractor.updateDoctor(new Doctor(
                    id,
                    firstName, lastName, middleName,
                    phoneNum,
                    doctorPassword, doctorEmail,
                    speciality, "ВераКлиник",
                    departmentName, officeNumber
            ));
            return "redirect:/admin/doctors/all";

        } catch (ValidationException e) {
            model.addAttribute("message", Errors.INVALID_DATA);
            return "error";

        } catch (UnableToUpdateException e) {
            model.addAttribute("message", Errors.UNABLE_TO_UPDATE);
            return "error";
        }
    }

    @GetMapping("/delete/{id}/{firstName}/{lastName}/{middleName}/" +
            "{phoneNum}/{doctorPassword}/{doctorEmail}/{speciality}/" +
            "{medicalCenterName}/{departmentName}/{officeNumber}"
    )
    public String delete(
            @PathVariable("id") int id,
            @PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName,
            @PathVariable("middleName") String middleName,
            @PathVariable("phoneNum") String phoneNum,
            @PathVariable("doctorPassword") String doctorPassword,
            @PathVariable("doctorEmail") String doctorEmail,
            @PathVariable("speciality") String speciality,
            @PathVariable("medicalCenterName") String medicalCenterName,
            @PathVariable("departmentName") String departmentName,
            @PathVariable("officeNumber") String officeNumber,
            Model model
    ) {

        try {
            doctorsInteractor.deleteDoctor(new Doctor(
                    id,
                    firstName, lastName, middleName,
                    phoneNum,
                    doctorPassword, doctorEmail,
                    speciality, medicalCenterName,
                    departmentName, officeNumber
            ));
            return "redirect:/admin/doctors/all";

        } catch (UnableToDeleteException e) {
            model.addAttribute("message", Errors.UNABLE_TO_DELETE);
            return "error";
        }
    }
}
