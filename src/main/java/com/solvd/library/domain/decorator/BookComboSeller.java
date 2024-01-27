package com.solvd.library.domain.decorator;

import com.solvd.library.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookComboSeller implements BookSeller {
    private static final Logger LOGGER = LogManager.getLogger(BookComboSeller.class);
    @Override
    public void sellBook() {
        LOGGER.info("This book combo is sold.");
    }
}
