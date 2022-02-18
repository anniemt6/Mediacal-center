package com.anna.proj2.controllers.admin;

import com.anna.proj2.controllers.admin.data.AdminCredentials;
import com.anna.proj2.controllers.util.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class SignupAdminController {

    @Autowired
    private AdminCredentials credentials;

    @GetMapping("/loginAdmin")
    public String signupAdminPage() {
        return "login";
    }

    @PostMapping("/loginAdmin")
    public String SignUpAdmin(
            @RequestParam("email") String login,
            @RequestParam("password") String password,
            Model model
    ) {

        if (credentials.matches(login, password)) {
            return "redirect:/admin/doctors/all";
        } else {
            model.addAttribute("message", Errors.INVALID_SIGNUP);
            return "error";
        }
    }
}
