package com.solvd.library.service.impl;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Inventory;
import com.solvd.library.service.BookInventoryListener;

import java.util.ArrayList;
import java.util.List;

public class ListenerHolder {
        private static List<BookInventoryListener> listeners = new ArrayList<>();

        public static void subscribe(BookInventoryListener bookInventoryListener) {
            listeners.add(bookInventoryListener);
        }

    public static void unsubscribe(BookInventoryListener bookInventoryListener) {
        listeners.remove(bookInventoryListener);
    }

    public static void addBookToInventory(String book, Inventory inventory) {
        // Notify
        for (BookInventoryListener listener : listeners) {
            listener.bookAddedToInventory(book, inventory);
        }
    }

}
