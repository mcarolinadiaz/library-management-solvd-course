package com.solvd.library.persistence;

import com.solvd.library.domain.*;
import com.solvd.library.domain.Book;
import com.solvd.library.domain.Comment;
import com.solvd.library.domain.User;

public interface CommentRepositoryRelationUser {
    void linkEntities(Comment t, User r);
    void unlinkEntities(Comment t, User r);
}
