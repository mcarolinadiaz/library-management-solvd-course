package com.solvd.library.persistence;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Publisher;

import java.sql.Connection;
import java.util.List;

public interface BookRepository extends GenericDAO<Book> {
    Book findById(Long id, Connection connection);
    List<Book> findAll(Connection connection);
    void create(Book entity, Connection connection);
    void update(Book entity, Connection connection);
    void delete(Long id, Connection connection);
}
