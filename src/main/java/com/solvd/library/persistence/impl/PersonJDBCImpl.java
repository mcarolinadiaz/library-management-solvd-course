package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Person;
import com.solvd.library.persistence.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonJDBCImpl implements PersonRepository {
    private static final Logger LOGGER = LogManager.getLogger(PersonJDBCImpl.class);
    private static final String JDBC_URL = "";
    private static final String JDBC_USER = "";
    private static final String JDBC_PASSWORD = "";
    private static final String SELECT_QUERY = "SELECT * FROM persons";
    private static final String DELETE_QUERY = "DELETE FROM persons WHERE id_person = ?";

    private Person extractPersonFromResultSet(ResultSet resultSet) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getLong("id_person"));
        person.setFirstName(resultSet.getString("f_name"));
        person.setLastName(resultSet.getString("l_name"));
        return person;
    }
    @Override
    public Person findById(Long id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + " WHERE id_person = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractPersonFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + ";")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    persons.add(extractPersonFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        }
        return persons;
    }

    @Override
    public void create(Person person) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO persons (f_name, l_name) VALUES (?, ?)")) {
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Person person) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE persons SET f_name = ?, l_name = ? WHERE id_person = ?")) {
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.setLong(3, person.getId());
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
