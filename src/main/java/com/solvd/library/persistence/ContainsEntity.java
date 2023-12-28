package com.solvd.library.persistence;

public interface ContainsEntity<T, R> {
    void addEntity(T t, R r);
    void deleteEntity(T t, R r);
}
