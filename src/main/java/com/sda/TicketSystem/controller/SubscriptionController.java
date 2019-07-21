package com.sda.TicketSystem.controller;

import com.sda.TicketSystem.model.SubscriptionDTO;
import com.sda.TicketSystem.service.PriceService;
import com.sda.TicketSystem.service.SecurityService;
import com.sda.TicketSystem.service.SubscriptionService;
import com.sda.TicketSystem.service.UserService;
import com.sda.TicketSystem.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping(value = "/private")
public class SubscriptionController {

    private UserService userService;
    private PriceService priceService;
    private SubscriptionService subscriptionService;
    private SecurityService securityService;
    private UserValidator userValidator;

    @Autowired
    public SubscriptionController(UserService userService, PriceService priceService, SubscriptionService subscriptionService, SecurityService securityService, UserValidator userValidator) {
        this.userService = userService;
        this.priceService = priceService;
        this.subscriptionService = subscriptionService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @GetMapping("/subscriptions")
    public String getAllSubscriptions(Model model) {
        List<SubscriptionDTO> subscriptionDTOList = subscriptionService.getAll();
        if (subscriptionDTOList.isEmpty()) {
            model.addAttribute("subscriptions_message", "Empty Subscritpions list !!!");
            model.addAttribute("subscriptionsList", null);
        } else {
            model.addAttribute("subscriptionsList", subscriptionDTOList);
        }
        return "subscriptions";
    }

}
