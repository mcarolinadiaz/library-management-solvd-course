package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Branch;
import com.solvd.library.persistence.BranchRepository;
import com.solvd.library.persistence.MyConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC implementation of the BranchRepository interface.
 */
public class BranchJDBCImpl implements BranchRepository {

    private static final Logger LOGGER = LogManager.getLogger(BranchJDBCImpl.class);
    private static final String SELECT_QUERY = "SELECT * FROM branches";
    private static final String DELETE_QUERY = "DELETE FROM branches WHERE id_branch = ?";
    private static final String INSERT_QUERY = "INSERT INTO branches (location) VALUES (?)";
    private static final String UPDATE_QUERY = "UPDATE branches SET location = ? WHERE id_branch = ?";
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
     * Extracts a Branch object from the ResultSet.
     *
     * @param resultSet The ResultSet containing Branch data.
     * @return A Branch object.
     * @throws SQLException If a SQL exception occurs.
     */
    private Branch extractBranchFromResultSet(ResultSet resultSet) throws SQLException {
        Branch branch = new Branch();
        branch.setId(resultSet.getLong("id_branch"));
        branch.setLocation(resultSet.getString("location"));
        return branch;
    }

    @Override
    public Branch findById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + " WHERE id_branch = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractBranchFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public List<Branch> findAll() {
        List<Branch> branches = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + ";")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    branches.add(extractBranchFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return branches;
    }

    /**
     * Configures the SQL declaration with entity data and executes it to create branches.
     *
     * @param branch The Branch to be created.
     */
    @Override
    public void create(Branch branch) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setString(1, branch.getLocation());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Branch branch) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, branch.getLocation());
            statement.setLong(2, branch.getId());
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
