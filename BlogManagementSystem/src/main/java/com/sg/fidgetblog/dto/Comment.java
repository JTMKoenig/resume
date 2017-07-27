/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author vincentsiciliano
 */
public class Comment {
    
    private int commentId;
    private int postId;
    private Post post;
    private int userId;
    private User user;
    private LocalDateTime commentDate;
    private String commentBody;
    private Boolean isArchive;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public Boolean getIsArchive() {
        return isArchive;
    }

    public void setIsArchive(Boolean isArchived) {
        this.isArchive = isArchived;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.commentId;
        hash = 47 * hash + this.postId;
        hash = 47 * hash + Objects.hashCode(this.post);
        hash = 47 * hash + this.userId;
        hash = 47 * hash + Objects.hashCode(this.user);
        hash = 47 * hash + Objects.hashCode(this.commentDate);
        hash = 47 * hash + Objects.hashCode(this.commentBody);
        hash = 47 * hash + Objects.hashCode(this.isArchive);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comment other = (Comment) obj;
        if (this.commentId != other.commentId) {
            return false;
        }
        if (this.postId != other.postId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.commentBody, other.commentBody)) {
            return false;
        }
        if (!this.post.equals(other.post)) {
            return false;
        }
        if (!this.user.equals(other.user)) {
            return false;
        }
        if (!(this.commentDate.compareTo(other.commentDate)==0)) {
            return false;
        }
        if (!Objects.equals(this.isArchive, other.isArchive)) {
            return false;
        }
        return true;
    }

    

    
    
    
    
}
