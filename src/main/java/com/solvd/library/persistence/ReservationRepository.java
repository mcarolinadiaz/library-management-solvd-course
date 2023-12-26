package com.solvd.library.persistence;

import com.solvd.library.domain.Reservation;

import java.util.List;

public interface ReservationRepository {
    Reservation findById(int id);
    List<Reservation> findAll();
    void save(Reservation reservation);
    void update(Reservation reservation);
    void delete(int id);
}
