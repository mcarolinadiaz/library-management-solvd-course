package com.solvd.library.service.impl;


import com.solvd.library.domain.Comment;
import com.solvd.library.domain.Loan;
import com.solvd.library.domain.User;
import com.solvd.library.persistence.UserRepository;
import com.solvd.library.persistence.impl.UserJDBCImpl;
import com.solvd.library.persistence.impl.UserMybatisImpl;
import com.solvd.library.service.CommentService;
import com.solvd.library.service.LoanService;
import com.solvd.library.service.UserService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final LoanService loanService = new LoanServiceImpl();
    private final CommentService commentService = new CommentServiceImpl();

    public UserServiceImpl() {
        this.userRepository = new UserJDBCImpl();
        //this.userRepository = new UserMybatisImpl();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        user.setId(null);
        userRepository.create(user);
        if (user.getLoans() != null) {
            List<Loan> loans = user.getLoans().stream()
                    .map(loan -> loanService.createLoan(loan))
                    .collect(Collectors.toList());
            user.setLoans(loans);
        }
        if (user.getComments() != null) {
            List<Comment> comments = user.getComments().stream()
                    .map(comment -> commentService.createComment(comment))
                    .collect(Collectors.toList());
            user.setComments(comments);
        }
        return user;
    }

    @Override
    public User updateUser(User user) {
        userRepository.update(user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }
}
