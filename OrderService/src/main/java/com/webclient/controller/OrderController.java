package com.webclient.controller;

import com.webclient.dto.Customer;
import com.webclient.dto.OrderResponse;
import com.webclient.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {


    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerId(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getCustomerById(id));
    }

    @GetMapping("/all")
    public Flux<Customer> getAllCustomers() {
        return orderService.getAllCustomers();
    }
}