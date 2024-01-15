package com.solvd.library.persistence;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Reservation;
import com.solvd.library.domain.User;

import java.sql.Connection;
import java.util.List;

public interface ReservationRepository extends GenericDAO<Reservation> {
    Reservation findById(Long id);
    List<Reservation> findAll();
    void create(Reservation reservation);
    void update(Reservation reservation);
}
