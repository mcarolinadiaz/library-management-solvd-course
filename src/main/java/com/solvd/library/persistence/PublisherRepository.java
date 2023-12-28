package com.solvd.library.persistence;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Publisher;

import java.sql.Connection;
import java.util.List;

public interface PublisherRepository extends GenericDAO<Publisher>, ContainsEntity<Publisher, Book> {
    Publisher findById(Long id, Connection connection);
    List<Publisher> findAll(Connection connection);
    void create(Publisher publisher, Connection connection);
    void update(Publisher publisher, Connection connection);
    void addEntity(Publisher p, Book b);
    void deleteEntity(Publisher p, Book b);
}
