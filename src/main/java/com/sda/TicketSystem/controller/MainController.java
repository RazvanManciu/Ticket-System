package com.sda.TicketSystem.controller;

import com.sda.TicketSystem.model.Subscription;
import com.sda.TicketSystem.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.TicketSystem.model.TicketDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @Autowired
    private SubscriptionService subscriptionService;

    @RequestMapping(value={"/"},
            method = RequestMethod.GET)
    public String index(Model model) {
        String user = "Admin";

        model.addAttribute("user", user);

        return "home";
    }

    @RequestMapping(value={"/access"},
            method = RequestMethod.POST)
    public String accesParking(@RequestBody String accessCode, Model model) {
        model.addAttribute("access_code", accessCode);

        return "home";
    }

    @RequestMapping(value={"/payments"},
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String payTicket(TicketDTO ticketDTO, Model model) {

        if(Integer.valueOf(ticketDTO.getTicketCode()) == 20) {
            String ticketAmount = ticketDTO.getTicketCode();
            model.addAttribute("ticket_amount", ticketAmount);
        }
        return "home";
    }

    @RequestMapping(value={"/exit"},
            method = RequestMethod.POST)
    public String exitParking(@RequestBody String exitCode, Model model) {
        String exit_code = exitCode;

        model.addAttribute("exit_code", exit_code);

        return "home";
    }

    @RequestMapping(value={"/subscription"},
            method = RequestMethod.POST)
    public String buySubscription(@RequestBody String exitCode, Model model) {
        String exit_code = exitCode;

        model.addAttribute("exit_code", exit_code);

        return "home";
    }

    @PostMapping
    public Subscription createSubscription(@RequestBody Subscription subscriptionCode){
        if(subscriptionCode == null){
            throw new RuntimeException("Subscription code does not exist");
        }
        return subscriptionService.create(subscriptionCode);
    }
}
