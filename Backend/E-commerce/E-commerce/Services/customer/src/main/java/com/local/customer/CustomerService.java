package com.local.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerReponsitory customerReponsitory;
    private final CustomerMapper customerMapper;

    public void updateCustomer(@Valid CustomerRequest customerRequest) {
        var customer = customerReponsitory.findById(customerRequest.id()).
                orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Không thể sửa dữ liệu với ID:: %s",  customerRequest)
                ));
        mergerCustomer(customer, customerRequest);
        customerReponsitory.save(customer);
    }

    private void mergerCustomer(Customer customer, @Valid CustomerRequest customerRequest) {
        if(StringUtils.isNotBlank(customerRequest.firstname())) {
            customer.setFirstname(customerRequest.firstname());
        }

        if(StringUtils.isNotBlank(customerRequest.lastname())) {
            customer.setLastname(customerRequest.lastname());
        }

        if(StringUtils.isNotBlank(customerRequest.email())) {
            customer.setEmail(customerRequest.email());
        }

        if(customerRequest.address() != null) {
            customer.setAddress(customerRequest.address());
        }
    }

    public String createCustomer( CustomerRequest customerRequest) {
        var customer = customerReponsitory.save(customerMapper.toCustomer(customerRequest));
        return customer.getId();
    }

    public List<CustomerReponse> findAllCustomer() {
        return customerReponsitory.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existIsById(String customerId) {
        return customerReponsitory.findById(customerId)
                .isPresent();
    }

    public CustomerReponse findById(String customerId) {
        return customerReponsitory.findById(customerId)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("khong co customer nao voi ID::%s", customerId)));
    }

    public void deleteCustomer(String customerId) {
        customerReponsitory.deleteById(customerId);
    }
}
