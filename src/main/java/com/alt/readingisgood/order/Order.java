package com.alt.readingisgood.order;

import com.alt.readingisgood.audit.Auditable;
import com.alt.readingisgood.book.Book;
import com.alt.readingisgood.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document("order")
public class Order  extends Auditable {
    @Id
    private String id;

    @DBRef
    private Customer customer;

    private String orderName;

    private List<OrderItem> products;

    private OrderStatus status;




}
