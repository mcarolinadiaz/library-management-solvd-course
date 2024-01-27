package com.solvd.library.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
@XmlAccessorType(XmlAccessType.FIELD)
public class Comment {
    @XmlAttribute(name = "id")
    private Long id;
    private String comment;
    private Long bookId;
    private Long userId;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public static Builder builder() {
        return new Builder(new Comment());
    }

    public static class Builder {
        public final Comment comment;
        public Builder(Comment comment) {
            this.comment = comment;
        }

        public Builder comment(String comment) {
            this.comment.comment = comment;
            return this;
        }

        public Builder bookId(Long bookId) {
            this.comment.bookId = bookId;
            return this;
        }

        public Builder userId(Long userId) {
            this.comment.userId = userId;
            return this;
        }

        public Comment build() {
            return comment;
        }


    }
}
