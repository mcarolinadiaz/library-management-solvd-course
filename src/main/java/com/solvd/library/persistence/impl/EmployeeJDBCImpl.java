package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Employee;
import com.solvd.library.persistence.EmployeeRepository;
import com.solvd.library.persistence.MyConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JDBC implementation of the EmployeeRepository interface.
 */
public class EmployeeJDBCImpl implements EmployeeRepository {

    private static final Logger LOGGER = LogManager.getLogger(EmployeeJDBCImpl.class);
    private static final String SELECT_QUERY = "SELECT * FROM employees";
    private static final String DELETE_QUERY = "DELETE FROM employees WHERE id_employee = ?";
    private static final String INSERT_QUERY = "INSERT INTO employees (id_branch, id_person) VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE employees SET id_branch = ?, id_person = ? WHERE id_employee = ?";
    private static final Connection connection;

    static {
        try {
            // Initialize the connection using MyConnectionPool
            connection = MyConnectionPool.getInstance().getConnection();
        } catch (InterruptedException e) {
            // Handle connection initialization exception
            throw new RuntimeException(e);
        }
    }

    /**
     * Extracts an Employee object from the ResultSet.
     *
     * @param resultSet The ResultSet containing Employee data.
     * @return An Employee object.
     * @throws SQLException If a SQL exception occurs.
     */
    private Employee extractEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getLong("id_employee"));
        employee.setBranchId(resultSet.getLong("id_branch"));
        employee.setPersonId(resultSet.getLong("id_person"));
        return employee;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + " WHERE id_employee = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(extractEmployeeFromResultSet(resultSet));
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
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + ";")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    employees.add(extractEmployeeFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        } finally {
            MyConnectionPool.returnConnectionToPool(connection);
        }
        return employees;
    }

    /**
     * Configures the SQL declaration with entity data and executes it to create employees.
     *
     * @param employee The Employee to be created.
     */
    @Override
    public void create(Employee employee) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, employee.getBranchId());
            statement.setLong(2, employee.getPersonId());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    employee.setId(generatedKeys.getLong(1));
                }
            }
        } catch (NullPointerException | SQLException e) {
            // Handle SQL exception
            LOGGER.error(e.getMessage());
        } finally {
            MyConnectionPool.returnConnectionToPool(connection);
        }
    }

    @Override
    public void update(Employee employee) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setLong(1, employee.getBranchId());
            statement.setLong(2, employee.getPersonId());
            statement.setLong(3, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error(e.getMessage());
        } finally {
            MyConnectionPool.returnConnectionToPool(connection);
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
        } finally {
            MyConnectionPool.returnConnectionToPool(connection);
        }
    }
}
