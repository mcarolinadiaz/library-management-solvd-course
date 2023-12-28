package com.solvd.library.persistence;

import com.solvd.library.domain.Loan;
import com.solvd.library.domain.User;

public interface UserRepositoryContainsLoans {
    void addEntity(User u, Loan l);
    void deleteEntity(User u, Loan l);
}
