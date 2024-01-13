package com.solvd.library.service.impl;


import com.solvd.library.domain.Comment;
import com.solvd.library.domain.Loan;
import com.solvd.library.domain.User;
import com.solvd.library.persistence.UserRepository;
import com.solvd.library.persistence.impl.UserJDBCImpl;
import com.solvd.library.service.CommentService;
import com.solvd.library.service.LoanService;
import com.solvd.library.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final LoanService loanService = new LoanServiceImpl();
    private final CommentService commentService = new CommentServiceImpl();

    public UserServiceImpl() {
        this.userRepository = new UserJDBCImpl();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user, Long personId, Long reservationId) {
        user.setId(null);
        userRepository.create(user, personId, reservationId);
        if (user.getLoans() != null) {
            List<Loan> loans = user.getLoans().stream()
                    .map(loan -> loanService.createLoan(loan, loan.getUserId(), user.getId()))
                    .collect(Collectors.toList());
            user.setLoans(loans);
        }
        if (user.getComments() != null) {
            List<Comment> comments = user.getComments().stream()
                    .map(comment -> commentService.createComment(comment, user.getId(), comment.getUserId()))
                    .collect(Collectors.toList());
            user.setComments(comments);
        }
        return user;
    }

    @Override
    public User updateUser(User user, Long personId, Long reservationId) {
        userRepository.update(user, personId, reservationId);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }
}
