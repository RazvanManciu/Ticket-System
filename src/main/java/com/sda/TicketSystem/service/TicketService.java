package com.sda.TicketSystem.service;

import com.sda.TicketSystem.model.Ticket;
import com.sda.TicketSystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {

    private TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket create(Ticket ticket){

        return ticketRepository.save(ticket);
    }

    public Ticket getByCode(String code){
        Optional<Ticket> ticket = ticketRepository.findByCode(code);
        return ticket.orElse(null);
    }

    public Ticket update(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id){
        ticketRepository.deleteById(id);
    }
}
