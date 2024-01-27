package com.solvd.library.persistence.impl;


public class AbstractRepositoryFactory {
    public static RepositoryFactory createFactoryRepository(String type) {
        return switch (type) {
            case "JDBC" -> new JDBCRepositoryFactory();
            case "Mybatis" -> new MybatisRepositoryFactory();
            default ->
                    throw new RuntimeException(String.format("Unable to create an object related to the '%s' type", type));
        };
    }
}
