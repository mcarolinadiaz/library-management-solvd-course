package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Person;
import com.solvd.library.persistence.PersonRepository;
import com.solvd.library.persistence.MyConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC implementation of the PersonRepository interface.
 */
public class PersonJDBCImpl implements PersonRepository {

    private static final Logger LOGGER = LogManager.getLogger(PersonJDBCImpl.class);
    private static final String SELECT_QUERY = "SELECT * FROM persons";
    private static final String DELETE_QUERY = "DELETE FROM persons WHERE id_person = ?";
    private static final String INSERT_QUERY = "INSERT INTO persons (f_name, l_name) VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE persons SET f_name = ?, l_name = ? WHERE id_person = ?";
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
     * Extracts a Person object from the ResultSet.
     *
     * @param resultSet The ResultSet containing Person data.
     * @return A Person object.
     * @throws SQLException If a SQL exception occurs.
     */
    private Person extractPersonFromResultSet(ResultSet resultSet) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getLong("id_person"));
        person.setFirstName(resultSet.getString("f_name"));
        person.setLastName(resultSet.getString("l_name"));
        return person;
    }

    @Override
    public Person findById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + " WHERE id_person = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractPersonFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + ";")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    persons.add(extractPersonFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return persons;
    }

    @Override
    public void create(Person person) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    person.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Person person) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.setLong(3, person.getId());
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
