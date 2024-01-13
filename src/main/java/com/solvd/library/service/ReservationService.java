package com.solvd.library.service;

import com.solvd.library.domain.Reservation;

import java.util.List;

public interface ReservationService {
    Reservation getAuthorById(Long id);
    List<Reservation> getAllAuthors();
    Reservation createAuthor(Reservation reservation);
    Reservation updateAuthor(Reservation reservation);
    void deleteAuthor(Long id);
}