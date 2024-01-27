package com.solvd.library.service;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Inventory;

public interface BookInventoryListener {
    void bookAddedToInventory(String book, Inventory inventory);
}

