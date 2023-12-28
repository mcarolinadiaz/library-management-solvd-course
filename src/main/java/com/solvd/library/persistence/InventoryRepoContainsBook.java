package com.solvd.library.persistence;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Inventory;

public interface InventoryRepoContainsBook {
    void linkEntities(Inventory i, Book b);
    void unlinkEntities(Inventory i, Book b);
}
