package com.sda.TicketSystem.controller;

import com.sda.TicketSystem.model.User;
import com.sda.TicketSystem.service.SecurityService;
import com.sda.TicketSystem.service.UserService;
import com.sda.TicketSystem.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserService userService;
    private SecurityService securityService;
    private UserValidator userValidator;

    @Autowired
    public UserController(UserService userService, SecurityService securityService, UserValidator userValidator) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult){
        userValidator.validate(userForm, bindingResult);

        if(bindingResult.hasErrors())
            return "registration";

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/private/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout){
        if(error != null)
            model.addAttribute("error", "Your username and password is invalid.");
        if(logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        return "login";
    }

    @GetMapping({"/private/welcome"})
    public String welcome(Model model){
        return "welcome";
    }

    @PostMapping("/private/welcome")
    public String welcomeWithPost(Model model){
/*        System.out.println(((User)model.asMap().get("userForm")).getUsername());
        System.out.println(((User)model.asMap().get("userForm")).getPassword());
        System.out.println(((User)model.asMap().get("userForm")).getRoles());

        model.addAttribute("welcome_message", "Welcome " + ((User)model.asMap().get("userForm")).getUsername());
 */
        return "redirect:/private/welcome";
    }
}
