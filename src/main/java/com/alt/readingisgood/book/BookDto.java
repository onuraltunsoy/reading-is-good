package com.alt.readingisgood.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BookDto {

    @NotBlank(message = "Name cannot be empty!")
    private String name;

    @NotBlank(message = "Author cannot be empty!")
    @Size(max = 255, message = "Author is too long!")
    private String author;

    @NotBlank(message = "ISBNNo cannot be empty!")
    private String isbnNo;

    @NotNull(message = "Price cannot be empty!")
    @Min(value = 0, message = "Price is not valid!")
    private Double price;

    @NotNull(message = "Stock cannot be empty!")
    @Min(value = 0, message = "Stock is not valid!")
    private Integer stock;
}
