package com.sda.TicketSystem.controller;

import com.sda.TicketSystem.model.PriceDTO;
import com.sda.TicketSystem.service.PriceService;
import com.sda.TicketSystem.service.SecurityService;
import com.sda.TicketSystem.service.UserService;
import com.sda.TicketSystem.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/private")
public class PriceController {

    private UserService userService;
    private PriceService priceService;
    private SecurityService securityService;
    private UserValidator userValidator;

    @Autowired
    public PriceController(UserService userService, PriceService priceService, SecurityService securityService, UserValidator userValidator) {
        this.userService = userService;
        this.priceService = priceService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @GetMapping("/prices")
    public String registration(Model model) {
        return "prices";
    }

    @RequestMapping(value = "/prices",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createPrice(PriceDTO priceDTO, Model model) {
        String createPriceMessage = getManagePriceMessage(priceDTO, model);
        model.addAttribute("create_price_messages", createPriceMessage);
        return "prices";
    }

    private String getManagePriceMessage(PriceDTO priceDTO, Model model) {
        String createPriceMessage;
        if (priceDTO.getPrice() != null && priceDTO.getType() != null) {
            if ("ticket".equals(priceDTO.getType()) || "subscription".equals(priceDTO.getType())) {
                if (Integer.valueOf(priceDTO.getPrice()) > 0) {
                    PriceDTO priceDTOFromDB = priceService.getByType(priceDTO.getType());
                    if (priceDTOFromDB != null) {
                        priceDTOFromDB.setPrice(priceDTO.getPrice());
                        try {
                            priceService.update(priceDTOFromDB);
                        } catch (Exception e) {
                            model.addAttribute("errorMessage", e.getMessage());
                        }
                        createPriceMessage = "For the card <b>" + priceDTO.getType() + "</b> you have this price: <b>" + priceDTO.getPrice() + "$</b>.";
                    } else {
                        priceService.create(priceDTO);
                        createPriceMessage = "The card <b>" + priceDTO.getType() + "</b> was created with price: <b>" + priceDTO.getPrice() + "$</b>.";
                    }
                } else {
                    createPriceMessage = "Invalid amount";
                }
            } else {
                createPriceMessage = "Invalid type";
            }

        } else {
            createPriceMessage = "Invalid fields";
        }
        return createPriceMessage;
    }
}
