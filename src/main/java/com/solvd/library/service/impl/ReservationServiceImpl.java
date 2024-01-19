package com.solvd.library.service.impl;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Reservation;
import com.solvd.library.domain.User;
import com.solvd.library.persistence.ReservationRepository;
import com.solvd.library.persistence.impl.ReservationJDBCImpl;
import com.solvd.library.persistence.impl.ReservationMybatisImpl;
import com.solvd.library.service.BookService;
import com.solvd.library.service.ReservationService;
import com.solvd.library.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReservationServiceImpl implements ReservationService {
    private static final Logger LOGGER = LogManager.getLogger(ReservationServiceImpl.class);

    private final ReservationRepository reservationRepository;
    private final BookService bookService = new BookServiceImpl();
    private final UserService userService = new UserServiceImpl();
    public ReservationServiceImpl() {
        //reservationRepository = new ReservationJDBCImpl();
        this.reservationRepository = new ReservationMybatisImpl();
    }
    @Override
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Collection<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        reservation.setId(null);
        reservationRepository.create(reservation);
        LOGGER.info(reservation.getId());
        if (reservation.getUsers() != null) {
            List<User> users = reservation.getUsers().stream()
                    .map(user -> userService.createUser(user))
                    .collect(Collectors.toList());
            reservation.setUsers(users);
        }
        if (reservation.getBooks() != null) {
            List<Book> books = reservation.getBooks().stream()
                    .map(book -> bookService.createBook(book))
                    .collect(Collectors.toList());
            reservation.setBooks(books);
        }
        return reservation;
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        reservationRepository.update(reservation);
        return reservation;
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.delete(id);
    }
}
