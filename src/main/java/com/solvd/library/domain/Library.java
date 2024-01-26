package com.solvd.library.domain;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "library")
@XmlAccessorType(XmlAccessType.FIELD)
public class Library {
    @XmlElementWrapper(name = "publishers")
    @XmlElement(name= "publisher")
    List<Publisher> publishers;
    @XmlElementWrapper(name = "categories")
    @XmlElement(name= "category")
    List<Category> categories;
    @XmlElementWrapper(name = "branches")
    @XmlElement(name= "branch")
    List<Branch> branches;
    @XmlElementWrapper(name = "reservations")
    @XmlElement(name= "reservation")
    List<Reservation> reservations;

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}
