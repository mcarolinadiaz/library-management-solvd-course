package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Author;
import com.solvd.library.persistence.AuthorRepository;
import com.solvd.library.persistence.MyConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC implementation of the AuthorRepository interface.
 */
public class AuthorJDBCImpl implements AuthorRepository {

    private static final Logger LOGGER = LogManager.getLogger(AuthorJDBCImpl.class);
    private static final String SELECT_QUERY = "SELECT * FROM authors";
    private static final String DELETE_QUERY = "DELETE FROM authors WHERE id_author = ?";
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
     * Extracts an Author object from the ResultSet.
     * @param resultSet The ResultSet containing Author data.
     * @return An Author object.
     * @throws SQLException
     */
    private Author extractAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getLong("id_author"));
        author.setName(resultSet.getString("name"));
        author.setNationality(resultSet.getString("nationality"));
        return author;
    }

    @Override
    public Author findById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + " WHERE id_author = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractAuthorFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + ";")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    authors.add(extractAuthorFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return authors;
    }

    /**
     * Configures the SQL declaration with entity data and executes it to create authors.
     * @param author The Author to be created.
     */
    @Override
    public void create(Author author) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO authors (name, nationality) VALUES (?, ?)")) {
            statement.setString(1, author.getName());
            statement.setString(2, author.getNationality());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Author author) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE authors SET name = ? WHERE id_author = ?")) {
            statement.setString(1, author.getName());
            statement.setLong(2, author.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM authors WHERE id_author = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error(e.getMessage());
        }
    }
}
