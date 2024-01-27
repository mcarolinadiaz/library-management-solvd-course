package com.solvd.library.domain.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AudioBookDecorator implements BookSeller {
    private static final Logger LOGGER = LogManager.getLogger(AudioBookDecorator.class);
    private int price;
    private final BookSeller bookSeller;
    public AudioBookDecorator(BookSeller bookSeller, int price) {
        this.bookSeller = bookSeller;
        this.price = price;
    }
    @Override
    public void sellBook() {
        bookSeller.sellBook();
        LOGGER.info("The cost audio book is "+ price);
    }
}
