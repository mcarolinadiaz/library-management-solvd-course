package com.solvd.library.domain;

import java.util.List;

public class Reservation {
    private Long id;
    private List<Book> books;
    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
