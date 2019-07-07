package com.sda.TicketSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value={"/"}, method = RequestMethod.GET)
    public String index(Model model) {
        String user = "Admin";

        model.addAttribute("user", user);

        return "home";
    }

    @RequestMapping(value={"/access"}, method = RequestMethod.POST)
    public String checkCode(@RequestBody String accessCode, Model model) {
        String user = "Admin";

        model.addAttribute("user", user);
        model.addAttribute("message2", accessCode);

        return "home";
    }
}
