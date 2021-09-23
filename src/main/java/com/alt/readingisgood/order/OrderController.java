package com.alt.readingisgood.order;

import com.alt.readingisgood.book.BookService;
import com.alt.readingisgood.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final BookService bookService;
    private final CustomerService customerService;


    @PostMapping()
    public ResponseEntity addOrder(@Valid @RequestBody OrderDto orderDto) {

        return ResponseEntity.ok(orderService.addOrder(orderDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity getOrderById(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/getOrdersByDateInterval")
    public ResponseEntity getOrdersByDateInterval(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate
            , @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return ResponseEntity.ok(orderService.getOrdersByDateInterval(startDate, endDate));
    }


}
