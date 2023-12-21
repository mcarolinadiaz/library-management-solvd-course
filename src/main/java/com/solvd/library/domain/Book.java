package com.solvd.library.domain;

public class Book {
    private String name;
    private Long id;
    public Book(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
