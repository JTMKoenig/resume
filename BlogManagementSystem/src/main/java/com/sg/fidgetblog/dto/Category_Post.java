/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dto;

import java.util.Objects;

/**
 *
 * @author vincentsiciliano
 */
public class Category_Post {

    private int categoryPostId;
    private String categoryId;
    private int postId;

    private Category category;
    private Post post;

    public int getCategoryPostId() {
        return categoryPostId;
    }

    public void setCategoryPostId(int categoryPostId) {
        this.categoryPostId = categoryPostId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.categoryPostId;
        hash = 37 * hash + Objects.hashCode(this.categoryId);
        hash = 37 * hash + this.postId;
        hash = 37 * hash + Objects.hashCode(this.category);
        hash = 37 * hash + Objects.hashCode(this.post);
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
        final Category_Post other = (Category_Post) obj;
        if (this.categoryPostId != other.categoryPostId) {
            return false;
        }
        if (this.postId != other.postId) {
            return false;
        }
        if (!this.categoryId.equals(other.categoryId)) {
            return false;
        }
        if (!this.category.equals(other.category)) {
            return false;
        }
        if (!this.post.equals(other.post)) {
            return false;
        }
        return true;
    }

    

}
