package com.alt.readingisgood.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderDto {
    @NotBlank(message = "Order name cannot be empty!")
    @Size(max = 255, message = "Order name is too long!")
    private String orderName;

    private List<OrderItemDto> books;

    private String customerId;

}
