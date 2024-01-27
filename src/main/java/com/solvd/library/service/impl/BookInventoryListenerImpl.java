package com.solvd.library.service.impl;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Inventory;
import com.solvd.library.service.BookInventoryListener;

public class BookInventoryListenerImpl implements BookInventoryListener {
    @Override
    public void bookAddedToInventory(String book, Inventory inventory) {
        System.out.println("The book'" + book + "' is available ans has '"
                + inventory.getStockQuantity() + " in stock'");
    }

}
