package com.springapps.cinema.B_service;

import com.springapps.cinema.C_repository.TicketRepository;
import com.springapps.cinema.D_model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {
    private TicketRepository ticketRepository;
    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    @Transactional
    public Double computeTicketPrice (Ticket ticket){
        return ticket.getSeat().getExtraPrice() + ticket.getProjection().getMovie().getMoviePrice();
    }
}
