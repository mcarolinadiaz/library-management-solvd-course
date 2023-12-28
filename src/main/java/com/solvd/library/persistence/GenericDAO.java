package com.solvd.library.persistence;

import java.sql.Connection;
import java.util.List;

public interface GenericDAO<T> {
    T findById(Long id, Connection connection);
    List<T> findAll(Connection connection);
    void create(T entity, Connection connection);
    void update(T entity, Connection connection);
    void delete(Long id, Connection connection);
}
