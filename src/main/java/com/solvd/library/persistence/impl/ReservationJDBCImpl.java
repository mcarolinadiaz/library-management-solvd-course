package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Reservation;
import com.solvd.library.persistence.ReservationRepository;
import com.solvd.library.persistence.MyConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC implementation of the ReservationRepository interface.
 */
public class ReservationJDBCImpl implements ReservationRepository {

    private static final Logger LOGGER = LogManager.getLogger(ReservationJDBCImpl.class);
    private static final String SELECT_QUERY = "SELECT * FROM reservations";
    private static final String DELETE_QUERY = "DELETE FROM reservations WHERE id_reservation = ?";
    private static final String INSERT_QUERY = "INSERT INTO reservations (reservation_date) VALUES (?)";
    private static final String UPDATE_QUERY = "UPDATE reservations SET reservation_date = ? WHERE id_reservation = ?";
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
     * Extracts a Reservation object from the ResultSet.
     * @param resultSet The ResultSet containing Reservation data.
     */
    private Reservation extractReservationFromResultSet(ResultSet resultSet) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(resultSet.getLong("id_reservation"));
        reservation.setReservationDate(resultSet.getDate("reservation_date"));
        return reservation;
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
    public Reservation findById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + " WHERE id_reservation = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractReservationFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        } finally {
            MyConnectionPool.returnConnectionToPool(connection);
        }
        return null;
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservations = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + ";")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    reservations.add(extractReservationFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        } finally {
            MyConnectionPool.returnConnectionToPool(connection);
        }
        return reservations;
    }

    @Override
    public void create(Reservation reservation) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setTimestamp(1, new java.sql.Timestamp(reservation.getReservationDate().getTime()));
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reservation.setId(generatedKeys.getLong(1));
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
    public void update(Reservation reservation) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setTimestamp(1, Timestamp.valueOf(reservation.getReservationDate().toString()));
            statement.setLong(2, reservation.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error(e.getMessage());
        } finally {
            MyConnectionPool.returnConnectionToPool(connection);
        }
    }
}
