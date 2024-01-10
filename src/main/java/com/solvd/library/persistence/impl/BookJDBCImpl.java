package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Category;
import com.solvd.library.domain.Publisher;
import com.solvd.library.domain.Reservation;
import com.solvd.library.persistence.BookRepoRelationalCategory;
import com.solvd.library.persistence.BookRepoRelationalPublisher;
import com.solvd.library.persistence.BookRepoRelationalReservation;
import com.solvd.library.persistence.BookRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BookJDBCImpl implements BookRepository, BookRepoRelationalReservation, BookRepoRelationalCategory, BookRepoRelationalPublisher {


    @Override
    public Book findById(Long id) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public void create(Book entity) {

    }

    @Override
    public void update(Book entity) {

    }

    @Override
    public void delete(Long id, Connection connection) {

    }

    public void linkEntities(Book b, Category c) {
    }

    @Override
    public void unlinkEntities(Book b, Category c) {

    }

    @Override
    public void linkEntities(Book b, Publisher p) {

    }

    @Override
    public void unlinkEntities(Book b, Publisher p) {

    }

    @Override
    public void linkEntities(Book b, Reservation r) {

    }

    @Override
    public void unlinkEntities(Book b, Reservation r) {

    }
}
