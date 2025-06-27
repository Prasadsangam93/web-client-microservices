package com.webclient.controller;

import com.webclient.dto.OrderResponse;
import com.webclient.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {


    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place/{customerId}")
    public ResponseEntity<OrderResponse> placeOrder(@PathVariable Long customerId) {
        return ResponseEntity.ok(orderService.placeOrder(customerId));
    }
}
