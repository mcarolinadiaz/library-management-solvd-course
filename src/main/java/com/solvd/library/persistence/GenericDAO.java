package com.solvd.library.persistence;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> {
    Optional<T> findById(Long id);
    Collection<T> findAll();
    /*
    void create(T entity);
    void update(T entity);*/
    void delete(Long id);
}
