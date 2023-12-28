package com.solvd.library.persistence;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Category;
import com.solvd.library.domain.Publisher;

public interface BookRepoRelationalCategory {
    void linkEntities(Book b, Category c);
    void unlinkEntities(Book b, Category c);
}
