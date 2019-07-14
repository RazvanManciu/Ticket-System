package com.sda.TicketSystem.service;

import com.sda.TicketSystem.model.Ticket;
import com.sda.TicketSystem.model.TicketDTO;
import com.sda.TicketSystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class TicketService {

    private TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
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
        if(ticket.isPresent()){
            TicketDTO ticketDTO = new TicketDTO();
            ticketDTO.setTicketCode(ticket.get().getCode());
            ticketDTO.setEnterDate(ticket.get().getEnterDate());
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
}
