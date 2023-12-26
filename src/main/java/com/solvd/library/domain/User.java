package com.solvd.library.domain;

import java.util.List;

public class User {
    private Long id;
    private Person person;
    private List<Comment> comments;
    private List<Loan> loans;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
