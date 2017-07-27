/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dto;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author vincentsiciliano
 */
public class Graphic {
    
    private int imageId;
    private byte[] image;
    private Post post;
    private User user;
    private String imageAltText;
    private boolean isArchive;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImageAltText() {
        return imageAltText;
    }

    public void setImageAltText(String imageAltText) {
        this.imageAltText = imageAltText;
    }

    public boolean isIsArchive() {
        return isArchive;
    }

    public void setIsArchive(boolean isArchive) {
        this.isArchive = isArchive;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.imageId;
        hash = 59 * hash + Arrays.hashCode(this.image);
        hash = 59 * hash + Objects.hashCode(this.post);
        hash = 59 * hash + Objects.hashCode(this.user);
        hash = 59 * hash + Objects.hashCode(this.imageAltText);
        hash = 59 * hash + (this.isArchive ? 1 : 0);
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
        final Graphic other = (Graphic) obj;
        if (this.imageId != other.imageId) {
            return false;
        }
        if (this.isArchive != other.isArchive) {
            return false;
        }
        if (!Objects.equals(this.imageAltText, other.imageAltText)) {
            return false;
        }
        if (!Arrays.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.post, other.post)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

    
    
     
    
}
