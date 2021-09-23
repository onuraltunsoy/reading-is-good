package com.alt.readingisgood.order;

import com.alt.readingisgood.book.Book;
import com.alt.readingisgood.book.BookRepository;
import com.alt.readingisgood.customer.Customer;
import com.alt.readingisgood.customer.CustomerRepository;
import com.alt.readingisgood.exception.AppException;
import com.mongodb.MongoCommandException;
import com.mongodb.MongoException;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.MongoTransactionException;
import org.springframework.data.mongodb.UncategorizedMongoDbException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;

    @Retryable(value = {MongoCommandException.class, MongoException.class}, exclude = {MongoTransactionException.class, UncategorizedMongoDbException.class},
            backoff = @Backoff(delay = 10), maxAttempts = 10)
    public Order addOrder(OrderDto orderDto){
        Order order = new Order();
        order.setOrderName(orderDto.getOrderName());
        List<OrderItem> orderItems = new ArrayList<>();
        orderDto.getBooks().forEach(item -> {
            Book book = bookRepository.findById(item.getBookId()).orElseThrow(() -> new AppException("Book not found!"));
            orderItems.add(new OrderItem(book, item.getQuantity()));
        });
        order.setProducts(orderItems);
        Customer customer = customerRepository.findById(orderDto.getCustomerId()).orElseThrow(() -> new AppException("Book not found!"));
        order.setCustomer(customer);
        order.setStatus(OrderStatus.PURCHASED);
        return orderRepository.save(order);
    }

    public OrderResponseDto getOrderById(String id){
        return OrderMapper.INSTANCE.toOrderResponseDto(orderRepository.findById(id).get());
    }

    @Transactional(readOnly = true)
    public List<Order> getOrdersByDateInterval(Date startDate ,Date endDate){
        LocalDateTime startLocalDate = LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime endLocalDate = LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault());

        return orderRepository.findAllByCreatedDateIsBetween(startLocalDate, endLocalDate);
    }

    @Transactional(readOnly = true)
    public List<Order> findAllByStatusAndCustomerId(String status, String customerId) {
        return orderRepository.findAllByStatusAndCustomer_Id(status, customerId);
    }

}
