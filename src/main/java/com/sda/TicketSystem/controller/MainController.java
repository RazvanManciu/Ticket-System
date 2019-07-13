package com.sda.TicketSystem.controller;

import com.sda.TicketSystem.model.AccessCodeDTO;
import com.sda.TicketSystem.model.SubscriptionDTO;
import com.sda.TicketSystem.model.TicketDTO;
import com.sda.TicketSystem.model.UserDTO;
import com.sda.TicketSystem.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Objects;

@Controller
public class MainController {

    private SubscriptionService subscriptionService;

    @Autowired
    public MainController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @RequestMapping(value = {"/"},
            method = RequestMethod.GET)
    public String index(Model model) {
        String user = "Admin";

        model.addAttribute("user", user);

        return "home";
    }

    @RequestMapping(value = {"/access"},
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String accesParking(AccessCodeDTO accessCodeDTO, Model model) {
        String code = accessCodeDTO.getAccessCode();
        if(Objects.nonNull(code) && !code.isEmpty()){
            // abonament
            SubscriptionDTO subscriptionDTO = subscriptionService.getByCode(code);
            if(Objects.nonNull(subscriptionDTO)){
                // subscription found
                model.addAttribute("access_message", "Access Granted !");
            } else {
                model.addAttribute("access_message", "Access NOT Granted !");
            }
        } else {
            model.addAttribute("access_message", "Generate ticket...");
        }
        model.addAttribute("access_code", accessCodeDTO.getAccessCode());

        return "home";
    }

    @RequestMapping(value = {"/payments"},
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String payTicket(TicketDTO ticketDTO, Model model) {

        if (Integer.valueOf(ticketDTO.getTicketCode()) == 20) {
            String ticketAmount = ticketDTO.getTicketCode();
            model.addAttribute("ticket_amount", ticketAmount);
        }
        return "home";
    }

    @RequestMapping(value = {"/exit"},
            method = RequestMethod.POST)
    public String exitParking(@RequestBody String exitCode, Model model) {
        String exit_code = exitCode;

        model.addAttribute("exit_code", exit_code);

        return "home";
    }

    @RequestMapping(value = {"/subscriptions"},
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String buySubscription(SubscriptionDTO subscriptionDTO, Model model) {

        model.addAttribute("sub_start_date", subscriptionDTO.getStartDate());
        model.addAttribute("sub_end_date", subscriptionDTO.getEndDate());

        SubscriptionDTO result = subscriptionService.create(subscriptionDTO);

        model.addAttribute("sub_code", subscriptionDTO.getCode());

        return "home";
    }

    @RequestMapping(value = {"/login"},
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String login(UserDTO userDTO, Model model) {

        model.addAttribute("user_name", userDTO.getUsername());
        model.addAttribute("password", userDTO.getPassword());

        return "home";
    }
}
