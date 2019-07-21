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
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public PriceDTO create(PriceDTO priceDTO) {
        Price price = new Price();
        price.setPrice(Integer.valueOf(priceDTO.getPrice()));
        price.setType(priceDTO.getType());

        priceRepository.save(price);

        PriceDTO priceDTOfromDB = new PriceDTO();
        priceDTOfromDB.setType(price.getType());
        priceDTOfromDB.setPrice(String.valueOf(price.getPrice()));

        return priceDTO;
    }

    public void update(PriceDTO priceDTO) {
        Price price = priceRepository.findByType(priceDTO.getType()).orElseThrow(() -> new RuntimeException("invalid type"));
        price.setPrice(Integer.valueOf(priceDTO.getPrice()));
        priceRepository.save(price);
    }

    public PriceDTO getByType(String type) {
        Optional<Price> price = priceRepository.findByType(type);
        if (price.isPresent()) {
            PriceDTO priceDTO = new PriceDTO();
            priceDTO.setType(price.get().getType());
            priceDTO.setPrice(String.valueOf(price.get().getPrice()));
            return priceDTO;
        }
        return null;
    }

    public void deletePrice(Long id) {
        priceRepository.deleteById(id);
    }

}
