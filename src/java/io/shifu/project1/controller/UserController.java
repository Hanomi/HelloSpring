package io.shifu.project1.controller;

import io.shifu.project1.model.User;
import io.shifu.project1.services.EmailService;
import io.shifu.project1.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import io.shifu.project1.services.UserService;
import io.shifu.project1.validator.UserValidator;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

/**
 * Controller for Users page
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        // Disable user until they click on confirmation link in email
        userForm.setEnabled(false);

        // Generate random 36-character string token for confirmation link
        userForm.setConfirmationToken(UUID.randomUUID().toString());

        userService.save(userForm);

        String appUrl = "http://localhost:8080";

        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(userForm.getEmail());
        registrationEmail.setSubject("Registration Confirmation");
        registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
                + appUrl + "/confirm?token=" + userForm.getConfirmationToken());
        registrationEmail.setFrom("noreply@domain.com");
        //emailService.sendEmail(registrationEmail);

        //securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        //return "redirect:/welcome";
        model.addAttribute("message", "A confirmation e-mail has been sent to " + userForm.getEmail());

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully");
        }

        return "login";
    }

    // Process confirmation link
    @RequestMapping(value="/confirm", method = RequestMethod.GET)
    public String confirm(Model model, @RequestParam("token") String token) {
        User user = userService.findByConfirmationToken(token);
        if (user == null) { // No token found in DB
            model.addAttribute("error", "Oops!  This is an invalid confirmation link.");
        } else { // Token found
            userService.activationUser(user);
        }
        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin";
    }
}
