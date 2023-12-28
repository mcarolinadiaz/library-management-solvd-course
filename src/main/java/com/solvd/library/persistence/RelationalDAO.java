package com.solvd.library.persistence;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Publisher;

public interface RelationalDAO<T, R> {
    void linkEntities(T t, R r);
    void unlinkEntities(T t, R r);
}
