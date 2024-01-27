package com.solvd.library.persistence.proxy;

import com.solvd.library.persistence.MyConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public class MyConnectionPoolProxy implements ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(MyConnectionPoolProxy.class);
    private final ConnectionPool realConnectionPool;

    public MyConnectionPoolProxy() {
        realConnectionPool = MyConnectionPool.getInstance();
    }

    @Override
    public Connection getConnection() throws InterruptedException {
        LOGGER.info("Proxy: Get connection");
        return realConnectionPool.getConnection();
    }


}