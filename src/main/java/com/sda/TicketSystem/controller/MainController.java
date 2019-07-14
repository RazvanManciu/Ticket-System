package com.sda.TicketSystem.controller;

import com.sda.TicketSystem.model.AccessCodeDTO;
import com.sda.TicketSystem.model.SubscriptionDTO;
import com.sda.TicketSystem.model.TicketDTO;
import com.sda.TicketSystem.model.UserDTO;
import com.sda.TicketSystem.service.SubscriptionService;
import com.sda.TicketSystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Controller
public class MainController {

    private SubscriptionService subscriptionService;
    private TicketService ticketService;
    private int ticketPricePerDay = 3;

    public MainController(SubscriptionService subscriptionService, TicketService ticketService) {
        this.subscriptionService = subscriptionService;
        this.ticketService = ticketService;
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
        String accessMessage = getAccessMessage(code, model);
        model.addAttribute("access_code", code);
        model.addAttribute("access_message", accessMessage);
        return "home";
    }

    @RequestMapping(value = {"/payments"},
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String payTicket(TicketDTO ticketDTO, Model model) {
        String ticketCode = ticketDTO.getTicketCode();
        TicketDTO ticketDTOFromDB = ticketService.getByCode(ticketCode);
        if (ticketDTOFromDB != null) {
            Period period = Period.between(ticketDTOFromDB.getEnterDate(), LocalDate.now());
            int numDays = period.getDays();
            int payedAmount = numDays * ticketPricePerDay;
            model.addAttribute("ticket_amount", payedAmount);
        }
        return "home";
    }

    @RequestMapping(value = {"/cash"},
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String ticketPayed(TicketDTO ticketDTO, Model model) {
        String ticketCode = ticketDTO.getTicketCode();
        TicketDTO ticketDTOFromDB = ticketService.getByCode(ticketCode);
        if (ticketDTOFromDB != null) {
            ticketDTOFromDB.setPayedAmount(ticketDTO.getPayedAmount());
            try {
                ticketService.update(ticketDTOFromDB);
            } catch (Exception e) {
                model.addAttribute("errorMessage", e.getMessage());
            }
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


    private String getAccessMessage(String code, Model model) {
        String accessMessage;
        if (Objects.nonNull(code) && !code.isEmpty()) {
            // it's a subscription code
            SubscriptionDTO subscriptionDTO = subscriptionService.getByCode(code);
            if (Objects.nonNull(subscriptionDTO)) {
                // subscription valid
                accessMessage = "Access Granted !";
            } else {
                // subscription invalid
                accessMessage = "Access NOT Granted !";
            }
        } else {
            // it's ticket time
            accessMessage = "Generating ticket...";
            TicketDTO ticketDTO = ticketService.create();
            model.addAttribute("new_ticket_code", ticketDTO.getTicketCode());
            model.addAttribute("new_ticket_enter_date", ticketDTO.getEnterDate());
        }
        return accessMessage;
    }
}
