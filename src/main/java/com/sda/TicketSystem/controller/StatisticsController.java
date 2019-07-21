package com.sda.TicketSystem.controller;

import com.sda.TicketSystem.model.ParkingSpaceDTO;
import com.sda.TicketSystem.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/private")
public class StatisticsController {

    private ParkingSpaceService parkingSpaceService;

    @Autowired
    public StatisticsController(ParkingSpaceService parkingSpaceService) {
        this.parkingSpaceService = parkingSpaceService;
    }

    @GetMapping("/statistics")
    public String getAllSpaces(Model model) {
        int spacesNumber = parkingSpaceService.getNumberOfSpaces();
        int freeSpacesNumber = parkingSpaceService.getNumberOfFreeSpaces();
        float freeSpacesPercent = 0.0f;
        if (spacesNumber != 0) {
            freeSpacesPercent = (freeSpacesNumber * 100.0f) / spacesNumber;
        }
        model.addAttribute("spacesNumber", spacesNumber);
        model.addAttribute("freeSpacesNumber", freeSpacesNumber);
        model.addAttribute("freeSpacesPercent", freeSpacesPercent);

        return "statistics";
    }
}
