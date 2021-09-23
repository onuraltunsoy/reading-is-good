package com.alt.readingisgood.order;

import com.alt.readingisgood.book.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderItem {
    @DBRef
    private Book book;

    private Integer quantity;
}
