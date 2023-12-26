package com.solvd.library.persistence;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Publisher;

import java.util.List;

public interface BookRepository {
    Book findById(int id);
    List<Book> findAll();
    void create(Book book);
    void update(Book book);
    void delete(int id);
    void linkEntities(Book b, Publisher p);
    void unlinkEntities(Book b, Publisher p);
}
