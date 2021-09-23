package com.alt.readingisgood.book;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostConstruct
    public void init() {
        BookDto bookDto = BookDto.builder().author("yazar1").isbnNo("1221333").price(150.0).stock(10).build();
        bookService.addBook(bookDto);
    }

    @PostMapping
    public BookDto addBook( @Valid @RequestBody BookDto bookDto) {
        return BookMapper.INSTANCE.toBookDTO(bookService.addBook(bookDto));
    }

    @PostMapping("/updateStock")
    @RequestMapping(value = "/updateStock", method = RequestMethod.POST)

    public BookDto updateStock(@Valid @RequestBody UpdateBookStockDto updateBookStockDto) {
        return BookMapper.INSTANCE.toBookDTO(bookService.updateStock(updateBookStockDto.getId(), updateBookStockDto.getCount()));
    }

}
