package com.alt.readingisgood.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UpdateBookStockDto {
    @NotBlank(message = "Book id cannot be empty!")
    private String id;

    @NotNull(message = "Count cannot be empty!")
    @Min(value = 0, message = "Count is not valid!")
    private Integer count;
}
