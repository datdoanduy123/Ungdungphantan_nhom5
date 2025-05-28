package com.local.customer;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")

@RequiredArgsConstructor

public class CustomerController {
    private final CustomerService customerService;
    private final CustomerReponsitory customerReponsitory;

    @PostMapping
    public ResponseEntity<String> createCustomer (
            @RequestBody @Valid CustomerRequest customerRequest) {
        return ResponseEntity.ok(customerService.createCustomer(customerRequest));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer (
            @RequestBody @Valid CustomerRequest customerRequest
    ) {
        customerService.updateCustomer(customerRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerReponse>> findAll() {
        return ResponseEntity.ok(customerService.findAllCustomer());
    }

    @GetMapping("/exits/{customer-id}")
    public ResponseEntity<Boolean> exitsById (
            @PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(customerService.existIsById(customerId));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerReponse> findById (
            @PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(customerService.findById(customerId));
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable("customer-id") String customerId
    ) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }
}
