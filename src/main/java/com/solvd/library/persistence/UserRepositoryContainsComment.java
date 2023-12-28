package com.solvd.library.persistence;

import com.solvd.library.domain.Comment;
import com.solvd.library.domain.User;

public interface UserRepositoryContainsComment {
    void addEntity(User u, Comment c);
    void deleteEntity(User u, Comment c);
}
