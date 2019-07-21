package com.sda.TicketSystem.service;

import com.sda.TicketSystem.model.Price;
import com.sda.TicketSystem.model.PriceDTO;
import com.sda.TicketSystem.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PriceService {
    private PriceRepository priceRepository;

    @Autowired
    public PriceService(PriceRepository priceRepository){
        this.priceRepository = priceRepository;
    }

    public Price create(PriceDTO priceDTO){
        Price price = new Price();
        price.setPrice(Integer.valueOf(priceDTO.getPrice()));
        price.setPrice((Integer.valueOf(priceDTO.getType())));
        return priceRepository.save(price);
    }

    public Price getById(Long id){
        Optional<Price> price = priceRepository.findById(id);
        return price.orElse(null);
    }

    public void deletePrice(Long id){
        priceRepository.deleteById(id);
    }

}
