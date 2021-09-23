package com.alt.readingisgood.order;

import com.alt.readingisgood.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderResponseDto  extends Auditable {

    private String id;

    private String customer_id;

    private String orderName;

    private List<OrderItem> products;

    private OrderStatus status;

}
