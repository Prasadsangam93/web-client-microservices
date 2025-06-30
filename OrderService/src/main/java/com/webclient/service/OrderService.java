package com.webclient.service;

import com.webclient.dto.Customer;
import com.webclient.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;


    public Customer getCustomerById(Long id) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:9091/api/customers/" + id)
                .retrieve()
                .bodyToMono(Customer.class)
                .block();

    }

    public Flux<Customer> getAllCustomers() {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:9091/api/customers/all")
                .retrieve()
                .bodyToFlux(Customer.class);


    }
}



