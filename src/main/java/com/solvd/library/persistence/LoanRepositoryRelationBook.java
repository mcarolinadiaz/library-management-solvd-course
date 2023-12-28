package com.solvd.library.persistence;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Loan;

public interface LoanRepositoryRelationBook {
    void linkEntities(Loan l, Book b);
    void unlinkEntities(Loan l, Book b);
}
