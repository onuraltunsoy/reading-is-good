package com.alt.readingisgood.book;

import com.alt.readingisgood.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public Book addBook(BookDto bookDto) {
        Book returnValue = null;
        Optional<Book> book = bookRepository.findByIsbnNo(bookDto.getIsbnNo());
        if (book.isPresent()) {
            book.get().setStock(book.get().getStock() + bookDto.getStock());
            returnValue = bookRepository.save(book.get());
        } else {
            returnValue = bookRepository.save(BookMapper.INSTANCE.toBook(bookDto));
        }
        return returnValue;

    }
    @Transactional
    public Book updateStock(String id, Integer count) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new AppException("Book not found!"));
        book.setStock(count);
        return bookRepository.save(book);
    }

}
