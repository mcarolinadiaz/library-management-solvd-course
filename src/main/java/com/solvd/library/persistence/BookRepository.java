package com.solvd.library.persistence;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Publisher;

import java.sql.Connection;
import java.util.List;

public interface BookRepository extends GenericDAO<Book> {
    Book findById(Long id);
    List<Book> findAll();
    void create(Book entity);
    void update(Book entity);
    void delete(Long id);
}
