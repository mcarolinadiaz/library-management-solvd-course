package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Loan;
import com.solvd.library.persistence.LoanRepository;
import com.solvd.library.persistence.MyConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC implementation of the LoanRepository interface.
 */
public class LoanJDBCImpl implements LoanRepository {

    private static final Logger LOGGER = LogManager.getLogger(LoanJDBCImpl.class);
    private static final String SELECT_QUERY = "SELECT * FROM loans";
    private static final String DELETE_QUERY = "DELETE FROM loans WHERE id_loan = ?";
    private static final String INSERT_QUERY = "INSERT INTO loans (loan_date, return_date, id_book, id_user) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE loans SET loan_date = ?, return_date = ?, id_book = ?, id_user = ? WHERE id_loan = ?";
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
     * Extracts a Loan object from the ResultSet.
     *
     * @param resultSet The ResultSet containing Loan data.
     * @return A Loan object.
     * @throws SQLException If a SQL exception occurs.
     */
    private Loan extractLoanFromResultSet(ResultSet resultSet) throws SQLException {
        Loan loan = new Loan();
        loan.setId(resultSet.getLong("id_loan"));
        loan.setLoanDate(resultSet.getDate("loan_date"));
        loan.setReturnDate(resultSet.getDate("return_date"));
        loan.setBookId(resultSet.getLong("id_book"));
        loan.setUserId(resultSet.getLong("id_user"));
        return loan;
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
    public Loan findById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + " WHERE id_loan = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractLoanFromResultSet(resultSet);
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
    public List<Loan> findAll() {
        List<Loan> loans = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + ";")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    loans.add(extractLoanFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        } finally {
            MyConnectionPool.returnConnectionToPool(connection);
        }
        return loans;
    }

    @Override
    public void create(Loan loan, Long userId, Long bookId) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setTimestamp(1, Timestamp.valueOf(loan.getLoanDate().toString()));
            statement.setTimestamp(2, Timestamp.valueOf(loan.getReturnDate().toString()));
            statement.setLong(3, bookId);
            statement.setLong(4, userId);
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    loan.setId(generatedKeys.getLong(1));
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
    public void update(Loan loan, Long userId, Long bookId) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setTimestamp(1, Timestamp.valueOf(loan.getLoanDate().toString()));
            statement.setTimestamp(2, Timestamp.valueOf(loan.getReturnDate().toString()));
            statement.setLong(3, bookId);
            statement.setLong(4, userId);
            statement.setLong(5, loan.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error(e.getMessage());
        } finally {
            MyConnectionPool.returnConnectionToPool(connection);
        }
    }
}
