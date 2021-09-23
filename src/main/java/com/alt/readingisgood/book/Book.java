package com.alt.readingisgood.book;

import com.alt.readingisgood.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document("book")
public class Book extends Auditable {
    @Id
    private String id;

    private String name;

    private String author;

    @Indexed(unique=true)
    private String isbnNo;

    private Double price;

    private Integer stock;

}
