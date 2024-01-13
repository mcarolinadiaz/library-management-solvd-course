package com.solvd.library.service.impl;


import com.solvd.library.domain.Inventory;
import com.solvd.library.persistence.InventoryRepository;
import com.solvd.library.persistence.impl.InventoryJDBCImpl;
import com.solvd.library.service.InventoryService;

import java.util.List;

public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl() {
        this.inventoryRepository = new InventoryJDBCImpl();
    }

    @Override
    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    @Override
    public List<Inventory> getAllInventorys() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory createInventory(Inventory inventory, Long branchId, Long bookId) {
        inventory.setId(null);
        inventoryRepository.create(inventory, branchId, bookId);
        return inventory;
    }

    @Override
    public Inventory updateInventory(Inventory inventory, Long branchId, Long bookId) {
        inventoryRepository.update(inventory, branchId, bookId);
        return inventory;
    }

    @Override
    public void deleteInventory(Long id) {
        inventoryRepository.delete(id);
    }
}
