package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Category;
import com.solvd.library.domain.Publisher;
import com.solvd.library.domain.Reservation;
import com.solvd.library.persistence.BookRepoRelationalCategory;
import com.solvd.library.persistence.BookRepoRelationalPublisher;
import com.solvd.library.persistence.BookRepoRelationalReservation;
import com.solvd.library.persistence.BookRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookJDBCImpl implements BookRepository, BookRepoRelationalReservation, BookRepoRelationalCategory, BookRepoRelationalPublisher {
    private static final Logger LOGGER = LogManager.getLogger(BookJDBCImpl.class);
    private static final String JDBC_URL = "";
    private static final String JDBC_USER = "";
    private static final String JDBC_PASSWORD = "";
    private static final String SELECT_QUERY = "SELECT * FROM books";
    private static final String DELETE_QUERY = "DELETE FROM books WHERE id_book = ?";
    private static final String INSERT_QUERY = "INSERT INTO books (name, year, id_publisher, id_category, id_reservation) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE books SET name = ?, year = ?, id_publisher = ?, id_category = ?, id_reservation = ? WHERE id_book = ?";
    private Book extractBookFromResultSet(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong("id_book"));
        book.setName(resultSet.getString("name"));
        book.setYear(resultSet.getDate("year"));
        book.setPublisherId(resultSet.getLong("id_publisher"));
        book.setCategoryId(resultSet.getLong("id_category"));
        book.setReservationId(resultSet.getLong("id_reservation"));
        return book;
    }

    @Override
    public Book findById(Long id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + " WHERE id_book = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractBookFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + ";")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    books.add(extractBookFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return books;
    }

    @Override
    public void create(Book book) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setString(1, book.getName());
            statement.setTimestamp(2, Timestamp.valueOf(book.getYear().toString()));
            statement.setLong(3, book.getPublisherId());
            statement.setLong(4, book.getCategoryId());
            statement.setLong(5, book.getReservationId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Book book) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, book.getName());
            statement.setTimestamp(2, Timestamp.valueOf(book.getYear().toString()));
            statement.setLong(3, book.getPublisherId());
            statement.setLong(4, book.getCategoryId());
            statement.setLong(5, book.getReservationId());
            statement.setLong(6, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
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
