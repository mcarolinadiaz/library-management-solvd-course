package com.solvd.library.persistence;

import com.solvd.library.domain.Inventory;

import java.util.List;

public interface InventoryRepository {
    Inventory findById(int id);
    List<Inventory> findAll();
    void save(Inventory inventory);
    void update(Inventory inventory);
    void delete(int id);
}
