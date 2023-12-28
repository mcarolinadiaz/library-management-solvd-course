package com.solvd.library.persistence;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Reservation;

public interface ReservationRepoContainsBook {
    void addEntity(Reservation r, Book b);
    void deleteEntity(Reservation r, Book b);
}
