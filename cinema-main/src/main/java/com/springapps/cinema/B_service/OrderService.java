package com.springapps.cinema.B_service;

import com.springapps.cinema.C_repository.OrderRepository;
import com.springapps.cinema.C_repository.ProjectionRepository;
import com.springapps.cinema.C_repository.SeatRepository;
import com.springapps.cinema.C_repository.UserRepository;
import com.springapps.cinema.D_model.*;
import com.springapps.cinema.E_DTO.OrderRequestDTO;
import com.springapps.cinema.E_DTO.TicketRequestDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    private UserRepository userRepository;

    private PdfGenerationService pdfGenerationService;

    private ProjectionRepository projectionRepository;

    private SeatRepository seatRepository;

    private TicketService ticketService;

    private EmailService emailService;
    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, PdfGenerationService pdfGenerationService, ProjectionRepository projectionRepository, SeatRepository seatRepository, TicketService ticketService, EmailService emailService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.pdfGenerationService = pdfGenerationService;
        this.projectionRepository = projectionRepository;
        this.seatRepository = seatRepository;
        this.ticketService = ticketService;
        this.emailService = emailService;
    }

    @Transactional
    public Order addOrder (OrderRequestDTO orderRequestDTO) throws MessagingException {
        //cautam proiectia dupa id
        //cautam user-ul dupa id

        User user = userRepository.findById(orderRequestDTO.getUserId()).orElseThrow(() -> new RuntimeException("user  not found"));
        Projection projection = projectionRepository.findById(orderRequestDTO.getProjectionID()).orElseThrow(() -> new RuntimeException("projection not found"));
        Order order = new Order();
        order.setUser(user);
        order.setTickets(generateOrderTicktes(order, projection, orderRequestDTO.getTicketRequestDTOs()));
        order.setTotalPrice(computeTotalPrice(order.getTickets()));
        Order savedOrder =  orderRepository.save(order);
        pdfGenerationService.generatePdf("ai cumparat bilet la filmul "+ projection.getMovie().getTitle(), "src/main/resources/order.pdf");
        emailService.sendMessageWithAttachment(user.getName(),"sdfdsf", "dsfdsf", "src/main/resources/order.pdf");
        return savedOrder;


        //salvam order
        //generam tickets in functie de locurile cerute in request

    }

    @Transactional
    public List<Ticket> generateOrderTicktes (Order order, Projection projection, List<TicketRequestDTO> ticketRequestDTOs){
        return ticketRequestDTOs.stream()
                .map(ticketRequestDTO -> mapFromDTOToTicket(ticketRequestDTO, order , projection))
                .collect(Collectors.toList());

    }

    @Transactional
    public Ticket mapFromDTOToTicket(TicketRequestDTO ticketRequestDTO, Order order, Projection projection){
        Ticket ticket = new Ticket();
        ticket.setOrder(order);
        ticket.setProjection(projection);
        Seat seat = seatRepository.findSeatBySeatRowAndSeatColumnAndCinemaRoom_Id(ticketRequestDTO.getRow(), ticketRequestDTO.getCol(), projection.getCinemaRoom().getId()).orElseThrow(()->new RuntimeException("seat not found"));
        if (!seat.isAvailable()){
            throw new RuntimeException("seat not available");
        }
        ticket.setSeat(seat);
        return ticket;
    }

    public Double computeTotalPrice (List<Ticket> tickets){
        return tickets.stream()
                .map(ticket -> ticketService.computeTicketPrice(ticket))
                .reduce((sum, ticketPrice) -> sum+ticketPrice)
                .orElseThrow(()-> new RuntimeException("total price could not be computed"));
    }
}
