package com.solvd.library.service;

import com.solvd.library.domain.Inventory;

import java.util.Collection;
import java.util.Optional;

public interface InventoryService {
    Optional<Inventory> getInventoryById(Long id);
    Collection<Inventory> getAllInventories();
    Inventory createInventory(Inventory inventory);
    Inventory updateInventory(Inventory inventory);
    void deleteInventory(Long id);
}