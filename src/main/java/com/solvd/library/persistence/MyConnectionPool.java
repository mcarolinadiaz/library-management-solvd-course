package com.solvd.library.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;



public class MyConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(MyConnectionPool.class);
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/library";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "mysql";
    private static BlockingQueue<Connection> pool;
    private static final int POOL_SIZE = 12;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            pool = new LinkedBlockingQueue<>(POOL_SIZE);
            initializePool();
        } catch (ClassNotFoundException e) {
            LOGGER.error("Error loading JDBC driver: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws InterruptedException {
        LOGGER.info("Get connection");
        return pool.take();
    }

    public static void returnConnectionToPool(Connection connection) {
        LOGGER.info("Return it");
        pool.offer(connection);
    }

    /**
     * Initialize collection connections.
     * It doesn't throw exception as we are mocking this connection pool.
     */
    private static void initializePool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                if (connection != null) {
                    pool.offer(connection);
                } else {
                    LOGGER.warn("Failed to create a database connection. Connection is null.");
                }
            } catch (SQLException e) {
                LOGGER.error("Error creating database connection: {}", e.getMessage());
            }
        }
    }
}
