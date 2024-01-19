package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Book;
import com.solvd.library.persistence.BookRepository;
import com.solvd.library.persistence.MyConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JDBC implementation of the BookRepository interface.
 */
public class BookJDBCImpl implements BookRepository {

    private static final Logger LOGGER = LogManager.getLogger(BookJDBCImpl.class);
    private static final String SELECT_QUERY = "SELECT * FROM books";
    private static final String DELETE_QUERY = "DELETE FROM books WHERE id_book = ?";
    private static final String INSERT_QUERY = "INSERT INTO books (name, year, id_publisher, id_category, id_reservation) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE books SET name = ?, year = ?, id_publisher = ?, id_category = ?, id_reservation = ? WHERE id_book = ?";
    private static final Connection connection;

    static {
        try {
            // Initialize the connection using MyConnectionPool
            connection = MyConnectionPool.getConnection();
        } catch (InterruptedException e) {
            // Handle connection initialization exception
            throw new RuntimeException(e);
        }
    }

    /**
     * Extracts a Book object from the ResultSet.
     *
     * @param resultSet The ResultSet containing Book data.
     * @return A Book object.
     * @throws SQLException If a SQL exception occurs.
     */
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
    public Optional<Book> findById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + " WHERE id_book = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(extractBookFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + ";")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    books.add(extractBookFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return books;
    }

    /**
     * Configures the SQL declaration with entity data and executes it to create books.
     *
     * @param book           The Book to be created.
     */
    @Override
    public void create(Book book) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setString(1, book.getName());
            statement.setTimestamp(2, Timestamp.valueOf(book.getYear().toString()));
            statement.setLong(3, book.getPublisherId());
            statement.setLong(4, book.getCategoryId());
            statement.setLong(5, book.getReservationId());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Book book) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, book.getName());
            statement.setTimestamp(2, Timestamp.valueOf(book.getYear().toString()));
            statement.setLong(3, book.getPublisherId());
            statement.setLong(4, book.getCategoryId());
            statement.setLong(5, book.getReservationId());
            statement.setLong(6, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error(e.getMessage());
        }
    }
}
