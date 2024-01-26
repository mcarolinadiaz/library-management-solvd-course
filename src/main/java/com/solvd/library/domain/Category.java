package com.solvd.library.domain;

import jakarta.xml.bind.annotation.*;

import java.util.List;
@XmlAccessorType(XmlAccessType.FIELD)
public class Category {
    @XmlAttribute(name = "id")
    private Long id;
    private String name;
    @XmlElementWrapper(name = "books")
    @XmlElement(name = "book")
    private List<Book> books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
