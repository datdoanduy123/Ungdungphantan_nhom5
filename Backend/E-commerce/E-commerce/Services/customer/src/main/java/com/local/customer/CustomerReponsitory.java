package com.local.customer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerReponsitory extends MongoRepository<Customer, String> {
}
