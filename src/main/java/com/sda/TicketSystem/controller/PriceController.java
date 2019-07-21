package com.sda.TicketSystem.controller;

import com.sda.TicketSystem.model.PriceDTO;
import com.sda.TicketSystem.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Objects;


@Controller
public class PriceController {

    private PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @RequestMapping(value = {"/prices"},
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createPrice(PriceDTO priceDTO, Model model) {
        String createPriceMessage;
        if (priceDTO.getPrice() != null && priceDTO.getType() != null) {
            if ("ticket".equals(priceDTO.getType()) || "subscription".equals(priceDTO.getType())) {
                if (Integer.valueOf(priceDTO.getPrice()) > 0) {
                    priceService.create(priceDTO);
                    createPriceMessage = "Valid input";
                } else {
                    createPriceMessage = "Invalid amount";
                }
            } else {
                createPriceMessage = "Invalid type";
            }

        } else {
            createPriceMessage = "Invalid fields";
        }
        model.addAttribute("create_price_messages", createPriceMessage);
        return "prices";
    }
}
