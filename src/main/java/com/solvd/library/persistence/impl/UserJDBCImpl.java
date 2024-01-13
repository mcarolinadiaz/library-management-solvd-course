package com.solvd.library.persistence.impl;

import com.solvd.library.domain.User;
import com.solvd.library.persistence.UserRepository;
import com.solvd.library.persistence.MyConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC implementation of the UserRepository interface.
 */
public class UserJDBCImpl implements UserRepository {

    private static final Logger LOGGER = LogManager.getLogger(UserJDBCImpl.class);
    private static final String SELECT_QUERY = "SELECT * FROM users";
    private static final String DELETE_QUERY = "DELETE FROM users WHERE id_user = ?";
    private static final String INSERT_QUERY = "INSERT INTO users (id_person, id_reservation) VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE users SET id_person = ?, id_reservation = ? WHERE id_user = ?";
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
     * Extracts a User object from the ResultSet.
     * @param resultSet The ResultSet containing User data.
     */
    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id_user"));
        user.setPersonId(resultSet.getLong("id_person"));
        user.setReservationId(resultSet.getLong("id_reservation"));
        return user;
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

    @Override
    public User findById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + " WHERE id_user = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractUserFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + ";")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(extractUserFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return users;
    }

    @Override
    public void create(User user, Long personId, Long reservationId) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, personId);
            statement.setLong(2, reservationId);
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(User user, Long personId, Long reservationId) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setLong(1, personId);
            statement.setLong(2, reservationId);
            statement.setLong(3, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error(e.getMessage());
        }
    }

}
