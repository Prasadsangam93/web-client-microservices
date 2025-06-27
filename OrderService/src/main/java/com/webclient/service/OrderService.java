package com.webclient.service;

import com.webclient.dto.Customer;
import com.webclient.dto.OrderResponse;
import com.webclient.entity.Order;
import com.webclient.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public OrderResponse placeOrder(Long customerId) {
        Customer customer = webClientBuilder.build()
                .get()
                .uri("http://localhost:9091/api/customers/" + customerId)
                .retrieve()
                .bodyToMono(Customer.class)
                .block();

        if (customer == null) {
            throw new RuntimeException("Customer not found with ID: " + customerId);
        }

        Order order = new Order();
        order.setCustomerId(customerId);
        order.setOrderNumber("ORD-" + System.currentTimeMillis()); // Generate unique order number
        orderRepository.save(order);

        return new OrderResponse(order,customer);
    }
}
//
//
//        Order order = new Order();
//        order.setCustomerId(customerId);
//        order.setOrderNumber("ORD-"  + customerId );
//        order = orderRepository.save(order);
//
//
//        Customer customer = webClientBuilder.build()
//                .get()
//                .uri("http://localhost:9091/api/customers/"+customerId)
//                .retrieve()
//                .bodyToMono(Customer.class)
//                .block();
//
//        return new OrderResponse(order, customer);
//
//    }





