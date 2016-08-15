package com.github.signal2564.magorarest.controllers;

import com.github.signal2564.magorarest.models.Order;
import com.github.signal2564.magorarest.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/1/orders", produces = APPLICATION_JSON_VALUE)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public Iterable<Order> list() {
        return orderService.list();
    }

    @PostMapping("/")
    public void create(Order order) {
        orderService.create(order);
    }

}
