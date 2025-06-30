package com.webclient.controller;

import com.webclient.entity.Customer;
import com.webclient.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        Customer saved = customerService.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

        @GetMapping("/{id}")
        public ResponseEntity<Customer> getCustomerById (@PathVariable Long id){
            return ResponseEntity.ok(customerService.getCustomerById(id));
        }
    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers(); // returns Flux
    }
}
