package com.solvd.library.persistence.impl;

import com.solvd.library.persistence.BranchRepository;

public class BranchRepositoryFactory {
    public static BranchRepository createBranchRepository(String type) {
        return switch (type) {
            case "JDBC" -> new BranchJDBCImpl();
            case "Mybatis" -> new BranchMybatisImpl();
            default ->
                    throw new RuntimeException(String.format("Unable to create an object related to the '%s' type", type));
        };
    }
}
