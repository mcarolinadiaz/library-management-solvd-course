package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Category;
import com.solvd.library.persistence.CategoryRepository;
import com.solvd.library.persistence.MyConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JDBC implementation of the CategoryRepository interface.
 */
public class CategoryJDBCImpl implements CategoryRepository {

    private static final Logger LOGGER = LogManager.getLogger(CategoryJDBCImpl.class);
    private static final String SELECT_QUERY = "SELECT * FROM categories";
    private static final String DELETE_QUERY = "DELETE FROM categories WHERE id_category = ?";
    private static final String INSERT_QUERY = "INSERT INTO categories (name) VALUES (?)";
    private static final String UPDATE_QUERY = "UPDATE categories SET name = ? WHERE id_category = ?";
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
     * Extracts a Category object from the ResultSet.
     * @param resultSet The ResultSet containing Category data.
     * @return
     * @throws SQLException
     */
    private Category extractCategoryFromResultSet(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getLong("id_category"));
        category.setName(resultSet.getString("name"));
        return category;
    }

    @Override
    public Optional<Category> findById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + " WHERE id_category = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(extractCategoryFromResultSet(resultSet));
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
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + ";")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    categories.add(extractCategoryFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        } finally {
            MyConnectionPool.returnConnectionToPool(connection);
        }
        return categories;
    }

    /**
     * Configures the SQL declaration with entity data and executes it to create categories.
     * @param category
     */
    @Override
    public void create(Category category) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, category.getName());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    category.setId(generatedKeys.getLong(1));
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
    public void update(Category category) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, category.getName());
            statement.setLong(2, category.getId());
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
