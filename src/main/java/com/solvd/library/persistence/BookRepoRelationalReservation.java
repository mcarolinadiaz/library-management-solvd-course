package com.solvd.library.persistence;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Publisher;
import com.solvd.library.domain.Reservation;

public interface BookRepoRelationalReservation {
    void linkEntities(Book b, Reservation r);
    void unlinkEntities(Book b, Reservation r);
}
