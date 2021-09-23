package com.alt.readingisgood.book;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void saveBookShouldSaveSuccessfully() {
        BookDto bookDto = BookDto.builder().author("yazar1").isbnNo("1221333").price(150.0).stock(10).build();
        Book book = Book.builder().author("yazar1").isbnNo("1221333").price(150.0).stock(10).id("123").build();
        when(bookRepository.save(Mockito.any())).thenReturn(book);

        Book saved = bookService.addBook(bookDto);
        assertEquals(saved.getId(), book.getId());
    }

    @Test
    public void saveBookShouldSaveSuccessfully2() {
        BookDto bookDto = BookDto.builder().author("yazar1").isbnNo("1221333").price(150.0).stock(10).build();

        Book book = Book.builder().author("yazar1").isbnNo("1221333").price(150.0).stock(10).id("123").build();

        Optional<Book> bookOptional = Optional.ofNullable(book);
        when(bookRepository.findByIsbnNo(Mockito.any())).thenReturn(bookOptional);
        when(bookRepository.save(Mockito.any())).thenReturn(book);

        Book saved = bookService.addBook(bookDto);
        assertEquals(saved.getId(), book.getId());
    }

    @Test
    public void updateBookStockShouldSuccessfully(){
        Book book = Book.builder().author("yazar1").isbnNo("1221333").price(150.0).stock(10).id("123").build();
        Integer updatingCount = 30;
        Optional<Book> bookOptional = Optional.of(book);

        when(bookRepository.findById(Mockito.any())).thenReturn(bookOptional);

        bookService.updateStock("123",updatingCount);
        verify(bookRepository, times(1)).findById(anyString());
        assertEquals(bookOptional.get().getStock(), updatingCount);

    }
}