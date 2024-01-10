package com.solvd.library.persistence.impl;

import com.solvd.library.Main;
import com.solvd.library.domain.Author;
import com.solvd.library.persistence.AuthorRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorJDBCImpl implements AuthorRepository {
    private static final Logger LOGGER = LogManager.getLogger(AuthorJDBCImpl.class);
    private static final String JDBC_URL = "";
    private static final String JDBC_USER = "";
    private static final String JDBC_PASSWORD = "";
    private static final String SELECT_QUERY = "SELECT * FROM authors";
    private static final String DELETE_QUERY = "DELETE FROM authors WHERE id_author = ?";

    private Author extractAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getLong("id_author"));
        author.setName(resultSet.getString("name"));
        author.setNationality(resultSet.getString("nationality"));
        return author;
    }

    @Override
    public Author findById(Long id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + "WHERE id_author = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractAuthorFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<Author>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + ";")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    authors.add(this.extractAuthorFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return authors;
    }

    /** Configurate the SQL declaration with entity data and execute it
     * @param author
     */
    @Override
    public void create(Author author) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO authors (name, nationality) " +
                    "VALUES ('Brandon Sanderson', 'united-statesian'), " +
                    "('Gabriel Garcia Marquez', 'colombian');")) {
                statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Author author) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                "UPDATE authors SET name = "+ author.getName() +
                        "WHERE id_author = "+ author.getId() +";")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM authors WHERE id_author like "+ id +";")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
