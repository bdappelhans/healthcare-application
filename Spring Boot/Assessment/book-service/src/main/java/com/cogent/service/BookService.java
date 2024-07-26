package com.cogent.service;

import com.cogent.entity.Book;
import com.cogent.repository.BookRepository;
import com.cogent.request.CreateBookRequest;
import com.cogent.response.BookResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    BookRepository bookRepository;

    public BookResponse createBook(CreateBookRequest createBookRequest) {
        Book book = new Book();
        book.setTitle(createBookRequest.getTitle());
        book.setAuthor(createBookRequest.getAuthor());
        bookRepository.save(book);
        return new BookResponse(book);
    }

    public BookResponse getBookById(int id) {
        logger.info("Getting book by ID " + id);
        Book book = bookRepository.findById(id).orElse(null);
        return new BookResponse(book);
    }
}
