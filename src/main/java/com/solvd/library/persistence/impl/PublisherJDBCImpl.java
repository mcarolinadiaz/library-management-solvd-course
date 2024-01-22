package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Publisher;
import com.solvd.library.persistence.PublisherRepository;
import com.solvd.library.persistence.MyConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JDBC implementation of the PublisherRepository interface.
 */
public class PublisherJDBCImpl implements PublisherRepository {

    private static final Logger LOGGER = LogManager.getLogger(PublisherJDBCImpl.class);
    private static final String SELECT_QUERY = "SELECT * FROM publishers";
    private static final String DELETE_QUERY = "DELETE FROM publishers WHERE id_publisher = ?";
    private static final String INSERT_QUERY = "INSERT INTO publishers (name) VALUES (?)";
    private static final String UPDATE_QUERY = "UPDATE publishers SET name = ? WHERE id_publisher = ?";
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
     * Extracts a Publisher object from the ResultSet.
     *
     * @param resultSet The ResultSet containing Publisher data.
     * @return A Publisher object.
     * @throws SQLException If a SQL exception occurs.
     */
    private Publisher extractPublisherFromResultSet(ResultSet resultSet) throws SQLException {
        Publisher publisher = new Publisher();
        publisher.setId(resultSet.getLong("id_publisher"));
        publisher.setName(resultSet.getString("name"));
        return publisher;
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error(e.getMessage());
        } finally {
            MyConnectionPool.returnConnectionToPool(connection);
        }
    }

    @Override
    public Optional<Publisher> findById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + " WHERE id_publisher = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(extractPublisherFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        } finally {
            MyConnectionPool.returnConnectionToPool(connection);
        }
        return Optional.empty();
    }

    @Override
    public List<Publisher> findAll() {
        List<Publisher> publishers = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + ";")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    publishers.add(extractPublisherFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        } finally {
            MyConnectionPool.returnConnectionToPool(connection);
        }
        return publishers;
    }

    @Override
    public void create(Publisher publisher) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, publisher.getName());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    publisher.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error(e.getMessage());
        } finally {
            MyConnectionPool.returnConnectionToPool(connection);
        }
    }

    @Override
    public void update(Publisher publisher) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, publisher.getName());
            statement.setLong(2, publisher.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error(e.getMessage());
        } finally {
            MyConnectionPool.returnConnectionToPool(connection);
        }
    }

}
