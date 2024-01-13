package com.solvd.library.service;

import com.solvd.library.domain.Book;

import java.util.List;

public interface BookService {
    Book getBookById(Long id);
    List<Book> getAllBooks();
    Book createBook(Book book, Long publisherId, Long categoryId, Long reservationId);
    Book updateBook(Book book, Long publisherId, Long categoryId, Long reservationId);
    void deleteBook(Long id);
}
