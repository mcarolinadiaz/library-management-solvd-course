package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Publisher;
import com.solvd.library.persistence.PublisherRepository;

import java.sql.Connection;
import java.util.List;

public class PublisherJDBCImpl implements PublisherRepository {

    @Override
    public void delete(Long id, Connection connection) {

    }

    @Override
    public Publisher findById(Long id, Connection connection) {
        return null;
    }

    @Override
    public List<Publisher> findAll(Connection connection) {
        return null;
    }

    @Override
    public void create(Publisher publisher, Connection connection) {

    }

    @Override
    public void update(Publisher publisher, Connection connection) {

    }

    @Override
    public void addEntity(Publisher p, Book b) {

    }

    @Override
    public void deleteEntity(Publisher p, Book b) {

    }
}
