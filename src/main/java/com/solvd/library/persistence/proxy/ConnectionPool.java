package com.solvd.library.persistence.proxy;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection() throws InterruptedException;
}