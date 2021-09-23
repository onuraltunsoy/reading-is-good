package com.alt.readingisgood.customer;

import com.alt.readingisgood.book.Book;
import com.alt.readingisgood.book.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto toCustomerDto(Customer customer);

    Customer toCustomer(CustomerDto customerDto);
}
