package com.solvd.library.persistence;

import java.sql.Connection;
import java.util.List;

public interface GenericDAO<T> {
    T findById(Long id);
    List<T> findAll();
    void create(T entity);
    void update(T entity);
    void delete(Long id);
}
