package com.solvd.library.persistence;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Branch;
import com.solvd.library.domain.Inventory;
import com.solvd.library.domain.User;

import java.sql.Connection;
import java.util.List;

public interface InventoryRepository extends GenericDAO<Inventory> {
    Inventory findById(Long id);
    List<Inventory> findAll();
    void create(Inventory inventory, Long branchId, Long bookId);
    void update(Inventory inventory, Long branchId, Long bookId);
}
