package com.solvd.library.service.impl;


import com.solvd.library.domain.Inventory;
import com.solvd.library.persistence.InventoryRepository;
import com.solvd.library.persistence.impl.InventoryJDBCImpl;
import com.solvd.library.persistence.impl.InventoryMybatisImpl;
import com.solvd.library.service.InventoryService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl() {
        //this.inventoryRepository = new InventoryJDBCImpl();
        this.inventoryRepository = new InventoryMybatisImpl();
    }

    @Override
    public Optional<Inventory> getInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    @Override
    public Collection<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory createInventory(Inventory inventory) {
        inventory.setId(null);
        inventoryRepository.create(inventory);
        return inventory;
    }

    @Override
    public Inventory updateInventory(Inventory inventory) {
        inventoryRepository.update(inventory);
        return inventory;
    }

    @Override
    public void deleteInventory(Long id) {
        inventoryRepository.delete(id);
    }
}
