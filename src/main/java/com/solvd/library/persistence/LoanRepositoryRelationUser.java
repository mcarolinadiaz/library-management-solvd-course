package com.solvd.library.persistence;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Loan;
import com.solvd.library.domain.User;

public interface LoanRepositoryRelationUser {
    void linkEntities(Loan l, User u);
    void unlinkEntities(Loan l, User u);
}
