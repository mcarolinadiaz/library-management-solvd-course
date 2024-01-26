package com.solvd.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.xml.bind.annotation.*;

import java.util.List;
@XmlAccessorType(XmlAccessType.FIELD)
public class Branch {
    @XmlAttribute(name = "id")
    private Long id;
    private String location;
    @JsonIgnore
    private List<Employee> employees;
    @XmlElementWrapper(name = "inventories")
    @XmlElement(name = "inventory")
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
    }
}
