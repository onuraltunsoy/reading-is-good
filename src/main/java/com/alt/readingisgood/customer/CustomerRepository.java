package com.alt.readingisgood.customer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {

    Customer findByUsername(String username);

    Boolean existsByUsername(String username);
}