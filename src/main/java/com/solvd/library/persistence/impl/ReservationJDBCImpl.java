package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Reservation;
import com.solvd.library.persistence.ReservationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationJDBCImpl implements ReservationRepository {
    private static final Logger LOGGER = LogManager.getLogger(ReservationJDBCImpl.class);
    private static final String JDBC_URL = "";  // Add your JDBC URL
    private static final String JDBC_USER = ""; // Add your JDBC user
    private static final String JDBC_PASSWORD = ""; // Add your JDBC password
    private static final String SELECT_QUERY = "SELECT * FROM reservations";
    private static final String DELETE_QUERY = "DELETE FROM reservations WHERE id_reservation = ?";
    private static final String INSERT_QUERY = "INSERT INTO reservations (reservation_date) VALUES (?)";
    private static final String UPDATE_QUERY = "UPDATE reservations SET reservation_date = ? WHERE id_reservation = ?";

    private Reservation extractReservationFromResultSet(ResultSet resultSet) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(resultSet.getLong("id_reservation"));
        reservation.setReservationDate(resultSet.getDate("reservation_date"));
        return reservation;
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

    @Override
    public Reservation findById(Long id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + " WHERE id_reservation = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractReservationFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservations = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + ";")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    reservations.add(extractReservationFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return reservations;
    }

    @Override
    public void create(Reservation reservation) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setTimestamp(2, Timestamp.valueOf(reservation.getReservationDate().toString()));
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reservation.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Reservation reservation) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setTimestamp(1, Timestamp.valueOf(reservation.getReservationDate().toString()));
            statement.setLong(2, reservation.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
