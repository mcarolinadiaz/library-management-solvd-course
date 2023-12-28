package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Reservation;
import com.solvd.library.domain.User;
import com.solvd.library.persistence.ReservationRepoContainsBook;
import com.solvd.library.persistence.ReservationRepoContainsUser;
import com.solvd.library.persistence.ReservationRepository;

import java.sql.Connection;
import java.util.List;

public class ReservationJDBCImpl implements ReservationRepository, ReservationRepoContainsBook, ReservationRepoContainsUser {

    @Override
    public void delete(Long id, Connection connection) {

    }

    @Override
    public void addEntity(Reservation r, Book b) {

    }

    @Override
    public void deleteEntity(Reservation r, Book b) {

    }

    @Override
    public void addEntity(Reservation r, User u) {

    }

    @Override
    public void deleteEntity(Reservation r, User u) {

    }

    @Override
    public Reservation findById(Long id, Connection connection) {
        return null;
    }

    @Override
    public List<Reservation> findAll(Connection connection) {
        return null;
    }

    @Override
    public void create(Reservation reservation, Connection connection) {

    }

    @Override
    public void update(Reservation reservation, Connection connection) {

    }
}
