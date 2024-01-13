package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Branch;
import com.solvd.library.domain.Employee;
import com.solvd.library.domain.Inventory;
import com.solvd.library.persistence.BranchRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BranchJDBCImpl implements BranchRepository {
    private static final Logger LOGGER = LogManager.getLogger(BranchJDBCImpl.class);
    private static final String JDBC_URL = "";
    private static final String JDBC_USER = "";
    private static final String JDBC_PASSWORD = "";
    private static final String SELECT_QUERY = "SELECT * FROM branches";
    private static final String DELETE_QUERY = "DELETE FROM branches WHERE id_branch = ?";
    private static final String INSERT_QUERY = "INSERT INTO branches (location) VALUES (?)";
    private static final String UPDATE_QUERY = "UPDATE branches SET location = ? WHERE id_branch = ?";
    private Branch extractBranchFromResultSet(ResultSet resultSet) throws SQLException {
        Branch branch = new Branch();
        branch.setId(resultSet.getLong("id_branch"));
        branch.setLocation(resultSet.getString("location"));
        return branch;
    }
    @Override
    public Branch findById(Long id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_QUERY +
                     " WHERE id_branch = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractBranchFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public List<Branch> findAll() {
        List<Branch> branches = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + ";")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    branches.add(extractBranchFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return branches;
    }

    @Override
    public void create(Branch branch) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setString(1, branch.getLocation());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Branch branch) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, branch.getLocation());
            statement.setLong(2, branch.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
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
}
