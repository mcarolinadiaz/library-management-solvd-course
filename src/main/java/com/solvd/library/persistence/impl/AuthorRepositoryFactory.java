package com.solvd.library.persistence.impl;

import com.solvd.library.persistence.AuthorRepository;

public class AuthorRepositoryFactory {
    public static AuthorRepository createAuthorRepository(String type) {
        return switch (type) {
            case "JDBC" -> new AuthorJDBCImpl();
            case "Mybatis" -> new AuthorMybatisImpl();
            default ->
                    throw new RuntimeException(String.format("Unable to create an object related to the '%s' type", type));
        };
    }
}
