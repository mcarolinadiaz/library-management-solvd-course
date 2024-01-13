package com.solvd.library.service;

import com.solvd.library.domain.Inventory;

import java.util.List;

public interface InventoryService {
    Inventory getInventoryById(Long id);
    List<Inventory> getAllInventorys();
    Inventory createInventory(Inventory inventory, Long branchId, Long bookId);
    Inventory updateInventory(Inventory inventory, Long branchId, Long bookId);
    void deleteInventory(Long id);
}