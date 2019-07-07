package com.sda.TicketSystem.controller;

import com.sda.TicketSystem.model.Subscription;
import com.sda.TicketSystem.repository.SubscriptionRepository;
import com.sda.TicketSystem.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Subscription createSubscription(@RequestBody Subscription subscriptionCode){
        if(subscriptionCode == null){
            throw new RuntimeException("Subscription code does not exist");
        }
        return subscriptionService.create(subscriptionCode);
    }
}
