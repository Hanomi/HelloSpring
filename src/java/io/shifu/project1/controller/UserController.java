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

        model.addAttribute("title", "Create an account");
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Create an account");
            return "registration";
        }

        // Disable user until they click on confirmation link in email
        userForm.setEnabled(false);

        // Generate random 36-character string token for confirmation link
        userForm.setConfirmationToken(UUID.randomUUID().toString());

        // Send email
        String appUrl = "http://localhost:8080";
        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(userForm.getEmail());
        registrationEmail.setSubject("Registration Confirmation");
        registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
                + appUrl + "/confirm?token=" + userForm.getConfirmationToken());
        registrationEmail.setFrom("nproject1meir@gmail.com");
        emailService.sendEmail(registrationEmail);

        // Save user
        userService.save(userForm);

        model.addAttribute("message", "A confirmation e-mail has been sent to " + userForm.getEmail());

        model.addAttribute("title", "Log in with your account");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Input is incorrect or account not active");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully");
        }

        model.addAttribute("title", "Log in with your account");
        return "login";
    }

    // Process confirmation link
    @RequestMapping(value="/confirm", method = RequestMethod.GET)
    public String confirm(Model model, @RequestParam("token") String token) {
        if (token == null || token.isEmpty()) {
            model.addAttribute("error", "Oops!  This is an invalid confirmation link.");
            return "login";
        }
        User user = userService.findByConfirmationToken(token);
        if (user == null) { // No token found in DB
            model.addAttribute("error", "Oops!  This is an invalid confirmation link.");
        } else { // Token found
            model.addAttribute("message", user.getUsername() + " success activated");
            userService.activationUser(user);
        }
        model.addAttribute("title", "Log in with your account");
        return "login";
    }
}
