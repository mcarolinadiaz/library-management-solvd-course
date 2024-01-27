package com.solvd.library.domain.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HardCoverDecorator implements BookSeller {
    private static final Logger LOGGER = LogManager.getLogger(HardCoverDecorator.class);
    private int price;
    private final BookSeller bookSeller;
    public HardCoverDecorator(BookSeller bookSeller, int price) {
        this.bookSeller = bookSeller;
        this.price = price;
    }
    @Override
    public void sellBook() {
        bookSeller.sellBook();
        LOGGER.info("The cost hard cover book is "+ price);
    }
}
