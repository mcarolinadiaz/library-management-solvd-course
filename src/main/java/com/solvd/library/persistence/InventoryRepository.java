package com.solvd.library.persistence;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Branch;
import com.solvd.library.domain.Inventory;
import com.solvd.library.domain.User;

import java.sql.Connection;
import java.util.List;

public interface InventoryRepository extends GenericDAO<Inventory> {
    Inventory findById(Long id, Connection connection);
    List<Inventory> findAll(Connection connection);
    void create(Inventory inventory, Connection connection);
    void update(Inventory inventory, Connection connection);
}
