package com.sda.TicketSystem.service;

import com.sda.TicketSystem.model.PriceDTO;
import com.sda.TicketSystem.model.Ticket;
import com.sda.TicketSystem.model.TicketDTO;
import com.sda.TicketSystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class TicketService {

    private TicketRepository ticketRepository;
    private PriceService priceService;

    @Autowired
    public TicketService(TicketRepository ticketRepository, PriceService priceService) {
        this.ticketRepository = ticketRepository;
        this.priceService = priceService;
    }

    public TicketDTO create() {
        Ticket ticket = new Ticket();
        ticket.setEnterDate(LocalDate.now());
//        ticket.setExitDate(ticket.getEnterDate().plusDays(1));

        String generatedTicketCode = "t" + Instant.now().toEpochMilli();
        ticket.setCode(generatedTicketCode);

        ticketRepository.save(ticket);

        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTicketCode(ticket.getCode());
        ticketDTO.setEnterDate(ticket.getEnterDate());

        return ticketDTO;
    }

    public TicketDTO getByCode(String code) {
        Optional<Ticket> ticket = ticketRepository.findByCode(code);
        if (ticket.isPresent()) {
            TicketDTO ticketDTO = new TicketDTO();
            ticketDTO.setTicketCode(ticket.get().getCode());
            ticketDTO.setEnterDate(ticket.get().getEnterDate());
            ticketDTO.setPayedAmount(ticket.get().getPayedAmount());
            ticketDTO.setPayDate(ticket.get().getPayDate());
            return ticketDTO;
        }
        return null;
    }

    public Ticket update(TicketDTO ticketDTO) {
        Ticket ticket = ticketRepository.findByCode(ticketDTO.getTicketCode()).orElseThrow(() -> new RuntimeException("invalid code"));
        ticket.setPayedAmount(ticketDTO.getPayedAmount());
        ticket.setPayDate(LocalDate.now());
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public Boolean isPayed(TicketDTO ticketDTO) {
        if ((LocalDate.now().equals(ticketDTO.getPayDate()))) {
            Period period = Period.between(ticketDTO.getEnterDate(), LocalDate.now());
            int numDays = period.getDays();

            int ticketPricePerDay = 3;
            PriceDTO priceDTO = priceService.getByType("ticket");
            if(priceDTO != null){
                ticketPricePerDay = Integer.valueOf(priceDTO.getPrice());
            }
            int payedAmount = numDays * ticketPricePerDay;
            if (payedAmount == ticketDTO.getPayedAmount()) {
                return true;
            }
        }
        return false;
    }
}
