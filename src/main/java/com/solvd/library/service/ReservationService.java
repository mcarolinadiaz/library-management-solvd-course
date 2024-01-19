package com.solvd.library.service;

import com.solvd.library.domain.Reservation;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Optional<Reservation> getReservationById(Long id);
    Collection<Reservation> getAllReservations();
    Reservation createReservation(Reservation reservation);
    Reservation updateReservation(Reservation reservation);
    void deleteReservation(Long id);
}