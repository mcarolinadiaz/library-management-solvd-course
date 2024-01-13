package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Loan;
import com.solvd.library.domain.User;
import com.solvd.library.persistence.LoanRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanJDBCImpl implements LoanRepository {
    private static final Logger LOGGER = LogManager.getLogger(LoanJDBCImpl.class);
    private static final String JDBC_URL = "";  // Add your JDBC URL
    private static final String JDBC_USER = ""; // Add your JDBC user
    private static final String JDBC_PASSWORD = ""; // Add your JDBC password
    private static final String SELECT_QUERY = "SELECT * FROM loans";
    private static final String DELETE_QUERY = "DELETE FROM loans WHERE id_loan = ?";
    private static final String INSERT_QUERY = "INSERT INTO loans (loan_date, return_date, id_book, id_user) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE loans SET loan_date = ?, return_date = ?, id_book = ?, id_user = ? WHERE id_loan = ?";

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
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Loan findById(Long id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + " WHERE id_loan = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractLoanFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public List<Loan> findAll() {
        List<Loan> loans = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + ";")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    loans.add(extractLoanFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return loans;
    }

    @Override
    public void create(Loan loan, Long userId, Long bookId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
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
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Loan loan, Long userId, Long bookId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setTimestamp(1, Timestamp.valueOf(loan.getLoanDate().toString()));
            statement.setTimestamp(2, Timestamp.valueOf(loan.getReturnDate().toString()));
            statement.setLong(3, bookId);
            statement.setLong(4, userId);
            statement.setLong(5, loan.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

}
