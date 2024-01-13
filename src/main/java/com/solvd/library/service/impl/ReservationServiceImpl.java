package com.solvd.library.service.impl;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Comment;
import com.solvd.library.domain.Reservation;
import com.solvd.library.domain.User;
import com.solvd.library.persistence.ReservationRepository;
import com.solvd.library.persistence.impl.ReservationJDBCImpl;
import com.solvd.library.service.BookService;
import com.solvd.library.service.ReservationService;
import com.solvd.library.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final BookService bookService = new BookServiceImpl();
    private final UserService userService = new UserServiceImpl();
    public ReservationServiceImpl() {
        reservationRepository = new ReservationJDBCImpl();
    }
    @Override
    public Reservation getAuthorById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public List<Reservation> getAllAuthors() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation createAuthor(Reservation reservation) {
        reservation.setId(null);
        reservationRepository.create(reservation);
        if (reservation.getUsers() != null) {
            List<User> users = reservation.getUsers().stream()
                    .map(user -> userService.createUser(user, user.getPersonId(), user.getReservationId()))
                    .collect(Collectors.toList());
            reservation.setUsers(users);
        }
        if (reservation.getBooks() != null) {
            List<Book> books = reservation.getBooks().stream()
                    .map(book -> bookService.createBook(book, book.getPublisherId(), book.getCategoryId(), book.getReservationId()))
                    .collect(Collectors.toList());
            reservation.setBooks(books);
        }
        return reservation;
    }

    @Override
    public Reservation updateAuthor(Reservation reservation) {
        reservationRepository.update(reservation);
        return reservation;
    }

    @Override
    public void deleteAuthor(Long id) {
        reservationRepository.delete(id);
    }
}
