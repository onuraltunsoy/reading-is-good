package com.alt.readingisgood.book;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto toBookDTO(Book book);

    Book toBook(BookDto bookDto);
}
