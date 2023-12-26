package com.solvd.library.domain;

import java.util.List;

public class Branch {
    private Long id;
    private String location;
    private List<Employee> employees;
    private List<Inventory> inventories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
