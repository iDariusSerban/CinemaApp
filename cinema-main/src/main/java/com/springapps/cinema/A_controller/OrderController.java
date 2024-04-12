package com.springapps.cinema.A_controller;

import com.springapps.cinema.B_service.OrderService;
import com.springapps.cinema.D_model.Movie;
import com.springapps.cinema.D_model.Order;
import com.springapps.cinema.E_DTO.MovieRequestDTO;
import com.springapps.cinema.E_DTO.OrderRequestDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        try {
            return ResponseEntity.ok(orderService.addOrder(orderRequestDTO));
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
