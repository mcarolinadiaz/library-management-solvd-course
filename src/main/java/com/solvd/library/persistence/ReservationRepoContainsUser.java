package com.solvd.library.persistence;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Reservation;
import com.solvd.library.domain.User;

public interface ReservationRepoContainsUser {
    void addEntity(Reservation r, User u);
    void deleteEntity(Reservation r, User u);
}
