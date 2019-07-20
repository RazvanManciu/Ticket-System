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

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Controller
public class MainController {

    private SubscriptionService subscriptionService;
    private TicketService ticketService;
    private int ticketPricePerDay = 3;

    @Autowired
    public MainController(SubscriptionService subscriptionService, TicketService ticketService) {
        this.subscriptionService = subscriptionService;
        this.ticketService = ticketService;
    }

    @RequestMapping(value = {"/"},
            method = RequestMethod.GET)
    public String index(Model model) {
/*        String user = "";
        model.addAttribute("user", user);*/
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
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String exitParking(AccessCodeDTO accessCodeDTO, Model model) {
        String exit_code = accessCodeDTO.getAccessCode();
        String exitMessage = getExitMessage(exit_code, model);
        model.addAttribute("exit_code", exit_code);
        model.addAttribute("exit_message", exitMessage);

        return "home";
    }

    @RequestMapping(value = {"/subscriptions"},
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String buySubscription(SubscriptionDTO subscriptionDTO, Model model) {

        if (subscriptionDTO.validateDates()) {
            model.addAttribute("sub_start_date", subscriptionDTO.getStartDate());
            model.addAttribute("sub_end_date", subscriptionDTO.getEndDate());

            SubscriptionDTO result = subscriptionService.create(subscriptionDTO);

            model.addAttribute("sub_code", result.getCode());
        } else {
            model.addAttribute("dates_error", "Please select both Start Date and End Date !!!");
        }

        return "home";
    }

    private String getAccessMessage(String code, Model model) {
        String accessMessage;
        if (Objects.nonNull(code) && !code.isEmpty()) {
            // it's a subscription code
            SubscriptionDTO subscriptionDTO = subscriptionService.getByCode(code);
            if (Objects.nonNull(subscriptionDTO)) {
                // subscription valid
                if (subscriptionDTO.getEndDate().compareTo(LocalDate.now()) > 0 &&
                        subscriptionDTO.getStartDate().compareTo(LocalDate.now()) < 0) {
                    accessMessage = "Access Granted !";
                } else {
                    accessMessage = "Outside the Subscription validity period. Access NOT Granted !";
                }
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

    private String getExitMessage(String code, Model model) {
        String exitMessage = null;
        if (Objects.nonNull(code) && !code.isEmpty()) {
            // code inserted
            if (code.substring(1).equals("s")) {
                SubscriptionDTO subscriptionDTO = subscriptionService.getByCode(code);
                if (Objects.nonNull(subscriptionDTO)) {
                    // subscription found
                    if (subscriptionDTO.getEndDate().compareTo(LocalDate.now()) > 0 &&
                            subscriptionDTO.getStartDate().compareTo(LocalDate.now()) < 0) {
                        exitMessage = "Exit Granted !";
                    } else {
                        exitMessage = "Outside the Subscription validity period. Exit NOT Granted !";
                    }
                } else {
                    // subscription invalid
                    exitMessage = "Subscription code invalid! Exit NOT Granted !";
                }
            } else if (code.substring(1).equals("t")) {
                TicketDTO ticketDTO = ticketService.getByCode(code);
                if (Objects.nonNull(ticketDTO)) {
                    //ticket found
                    if (ticketService.isPayed(ticketDTO)) {
                        exitMessage = "Exit Granted !";
                    } else {
                        exitMessage = "Ticket NOT payed! Exit NOT Granted !";
                    }
                } else {
                    // subscription invalid
                    exitMessage = "Ticket code invalid! Exit NOT Granted !";
                }
            } else {
                exitMessage = "Insert a valid code!";
            }
        } else {
            exitMessage = "Insert a valid code!";
        }
        return exitMessage;
    }
}
