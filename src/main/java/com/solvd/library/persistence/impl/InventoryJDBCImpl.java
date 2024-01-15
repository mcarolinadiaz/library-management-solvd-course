package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Inventory;
import com.solvd.library.persistence.InventoryRepository;
import com.solvd.library.persistence.MyConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC implementation of the InventoryRepository interface.
 */
public class InventoryJDBCImpl implements InventoryRepository {

    private static final Logger LOGGER = LogManager.getLogger(InventoryJDBCImpl.class);
    private static final String SELECT_QUERY = "SELECT * FROM inventories";
    private static final String DELETE_QUERY = "DELETE FROM inventories WHERE id_inventory = ?";
    private static final String INSERT_QUERY = "INSERT INTO inventories (stock_quantity, id_branch, id_book) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE inventories SET stock_quantity = ?, id_branch = ?, id_book = ? WHERE id_inventory = ?";
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
     * Extracts an Inventory object from the ResultSet.
     *
     * @param resultSet The ResultSet containing Inventory data.
     * @return An Inventory object.
     * @throws SQLException If a SQL exception occurs.
     */
    private Inventory extractInventoryFromResultSet(ResultSet resultSet) throws SQLException {
        Inventory inventory = new Inventory();
        inventory.setId(resultSet.getLong("id_inventory"));
        inventory.setStockQuantity(resultSet.getInt("stock_quantity"));
        inventory.setBranchId(resultSet.getLong("id_branch"));
        inventory.setBookId(resultSet.getLong("id_book"));
        return inventory;
    }

    @Override
    public Inventory findById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + " WHERE id_inventory = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractInventoryFromResultSet(resultSet);
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
    public List<Inventory> findAll() {
        List<Inventory> inventories = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY + ";")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    inventories.add(extractInventoryFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error("SQL Exception while executing query: {}", e.getMessage());
        } finally {
            MyConnectionPool.returnConnectionToPool(connection);
        }
        return inventories;
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
    public void create(Inventory inventory, Long branchId, Long bookId) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, inventory.getStockQuantity());
            statement.setLong(2, branchId);
            statement.setLong(3, bookId);
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    inventory.setId(generatedKeys.getLong(1));
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
    public void update(Inventory inventory, Long branchId, Long bookId) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setInt(1, inventory.getStockQuantity());
            statement.setLong(2, branchId);
            statement.setLong(3, bookId);
            statement.setLong(4, inventory.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exception
            LOGGER.error(e.getMessage());
        } finally {
            MyConnectionPool.returnConnectionToPool(connection);
        }
    }
}
