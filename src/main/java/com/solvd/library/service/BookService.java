package com.solvd.library.service;

import com.solvd.library.domain.Book;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> getBookById(Long id);
    Collection<Book> getAllBooks();
    Book createBook(Book book);
    Book updateBook(Book book);
    void deleteBook(Long id);
}
