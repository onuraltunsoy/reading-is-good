package com.alt.readingisgood.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {

    Page<Order> getOrderByCustomerId(String customerId, Pageable pageable);

    List<Order> findAllByCreatedDateIsBetween(LocalDateTime startDate , LocalDateTime endDate);

    List<Order> findAllByStatusAndCustomer_Id(String status, String customerId);


}


