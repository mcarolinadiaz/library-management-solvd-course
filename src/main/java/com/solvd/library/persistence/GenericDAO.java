package com.solvd.library.persistence;

import java.util.List;

public interface GenericDAO<T> {
    T findById(Long id);
    List<T> findAll();
    void create(T entity);
    void update(T entity);
    void delete(Long id);
    void linkEntities(T source, T target);
    void unlinkEntities(T source, T target);
}
