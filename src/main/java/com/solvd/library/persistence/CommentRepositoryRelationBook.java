package com.solvd.library.persistence;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Comment;

public interface CommentRepositoryRelationBook {
    void linkEntities(Comment t, Book r);
    void unlinkEntities(Comment t, Book r);
}
