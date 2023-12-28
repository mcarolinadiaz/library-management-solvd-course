package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Branch;
import com.solvd.library.domain.Inventory;
import com.solvd.library.persistence.InventoryRepoContainsBook;
import com.solvd.library.persistence.InventoryRepoContainsBranch;
import com.solvd.library.persistence.InventoryRepository;

import java.sql.Connection;
import java.util.List;

public class InventoryJDBCImpl implements InventoryRepository, InventoryRepoContainsBook, InventoryRepoContainsBranch {

    @Override
    public void delete(Long id, Connection connection) {

    }

    @Override
    public Inventory findById(Long id, Connection connection) {
        return null;
    }

    @Override
    public List<Inventory> findAll(Connection connection) {
        return null;
    }

    @Override
    public void create(Inventory inventory, Connection connection) {

    }

    @Override
    public void update(Inventory inventory, Connection connection) {

    }

    @Override
    public void linkEntities(Inventory i, Book b) {

    }

    @Override
    public void unlinkEntities(Inventory i, Book b) {

    }

    @Override
    public void linkEntities(Inventory i, Branch br) {

    }

    @Override
    public void unlinkEntities(Inventory i, Branch br) {

    }
}
