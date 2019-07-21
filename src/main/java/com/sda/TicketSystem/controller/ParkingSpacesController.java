package com.sda.TicketSystem.controller;

import com.sda.TicketSystem.model.ParkingSpace;
import com.sda.TicketSystem.model.ParkingSpaceDTO;
import com.sda.TicketSystem.model.SubscriptionDTO;
import com.sda.TicketSystem.service.*;
import com.sda.TicketSystem.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping(value = "/private")
public class ParkingSpacesController {

    private UserService userService;
    private PriceService priceService;
    private ParkingSpaceService parkingSpaceService;
    private SecurityService securityService;
    private UserValidator userValidator;

    @Autowired
    public ParkingSpacesController(UserService userService, PriceService priceService, ParkingSpaceService parkingSpaceService, SecurityService securityService, UserValidator userValidator) {
        this.userService = userService;
        this.priceService = priceService;
        this.parkingSpaceService = parkingSpaceService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @GetMapping("/spaces")
    public String getAllSpaces(Model model) {
        List<ParkingSpaceDTO> parkingSpaceDTOS = parkingSpaceService.getAll();
        if (parkingSpaceDTOS.isEmpty()) {
            model.addAttribute("parking_spaces_message", "Empty Parking Spaces list !!!");
            model.addAttribute("parkingSpaceList", null);
        } else {
            model.addAttribute("parkingSpaceList", parkingSpaceDTOS);
        }
        return "parkingspaces";
    }
}
