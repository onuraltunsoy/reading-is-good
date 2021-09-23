package com.alt.readingisgood.order;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Sort;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    List<OrderDto> toOrderDtoList( List<Order> orders);

    List<Order> toOrderList(List<OrderDto> orderDtos);

    OrderResponseDto toOrderResponseDto( Order orders);




}
