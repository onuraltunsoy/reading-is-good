package com.alt.readingisgood.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    void addOrder() {
    }

    @Test
    public void getOrderById() {
        Order order = new Order();
        order.setId("123");
        Optional<Order> orderOptional = Optional.ofNullable(order);

        when(orderRepository.findById(anyString())).thenReturn(orderOptional);
        orderService.getOrderById(anyString());
        verify(orderRepository, times(1)).findById(any());

    }

    @Test
    void getOrdersByDateInterval() {
    }

    @Test
    void findAllByStatusAndCustomerId() {
    }
}