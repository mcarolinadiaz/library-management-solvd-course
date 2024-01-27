package com.solvd.library.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.solvd.library.domain.decorator.AudioBookDecorator;
import com.solvd.library.domain.decorator.BookComboSeller;
import com.solvd.library.domain.decorator.BookSeller;
import com.solvd.library.domain.decorator.HardCoverDecorator;
import com.solvd.library.persistence.impl.BookJDBCImpl;
import com.solvd.library.service.BookInventoryListener;
import com.solvd.library.service.impl.ListenerHolder;
import jakarta.xml.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.Date;
import java.util.List;
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {
    private static final Logger LOGGER = LogManager.getLogger(Book.class);
    private String name;
    @XmlAttribute(name = "id")
    private Long id;
    @JsonIgnore
    private List<Comment> comments;
    @JsonIgnore
    private List<Loan> loans;
    @XmlElementWrapper(name = "inventories")
    @XmlElement(name = "inventory")
    private List<Inventory> inventories;
    private Long publisherId;
    private Long categoryId;
    private Long reservationId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date year;

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

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public List<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(Collection<Inventory> inventories) {
        try {
            for (Inventory inventory : inventories) {
                ListenerHolder.addBookToInventory(getName(), inventory);
                if (this.inventories != null) {
                    this.inventories.add(inventory);
                }
            }
            if (this.inventories == null) {
                this.inventories = (List<Inventory>) inventories;
            }
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage());
        }

    }

    public static void sellBook(boolean audio, boolean hardCover){
        BookSeller bookSeller = new BookComboSeller();
        if (audio) {
            bookSeller = new AudioBookDecorator(bookSeller, 10);
        }
        if (hardCover) {
            bookSeller = new HardCoverDecorator(bookSeller, 15);
        }
        bookSeller.sellBook();
    }
}
