package com.sda.TicketSystem.controller;

import com.sda.TicketSystem.model.SubscriptionDTO;
import com.sda.TicketSystem.model.TicketDTO;
import com.sda.TicketSystem.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

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
            method = RequestMethod.POST)
    public String accesParking(@RequestBody String accessCode, Model model) {
        model.addAttribute("access_code", accessCode);

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

/*    @ModelAttribute
    LocalDateTime initLocalDateTime() {
        return LocalDateTime.now();
    }*/

    @RequestMapping(value = {"/subscriptions"},
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String buySubscription(SubscriptionDTO subscriptionDTO,
                                  Model model) {

/*        //Create a DateTimeFormatter with your required format:
        DateTimeFormat dateTimeFormat =
                new DateTimeFormatter(DateTimeFormatter.BASIC_ISO_DATE);

        //Next parse the date from the @RequestParam, specifying the TO type as a TemporalQuery:
        LocalDateTime date = dateTimeFormat.parse(startDate, LocalDateTime::from);*/

//        System.out.println(startDate);
//        System.out.println(endDate);

//        SubscriptionDTO subscriptionDTO = new SubscriptionDTO(startDate, endDate);
//
//        model.addAttribute("sub_start_date", subscriptionDTO.getStartDate());
//        model.addAttribute("sub_end_date", endDate);

        return "home";
    }

/*    @PostMapping
    public Subscription createSubscription(@RequestBody Subscription subscriptionCode){
        if(subscriptionCode == null){
            throw new RuntimeException("Subscription code does not exist");
        }
        return subscriptionService.create(subscriptionCode);
    }*/
}
