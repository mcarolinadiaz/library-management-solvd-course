package com.solvd.library.persistence;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Publisher;

public interface BookRepoRelationalPublisher {
    void linkEntities(Book b, Publisher p);
    void unlinkEntities(Book b, Publisher p);
}
