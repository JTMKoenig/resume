/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 *
 * @author vincentsiciliano
 */
public class NewPost {

    private String newPostTitle;
    private String newPostUserName;
    private LocalDate newPostStartDate;
    private LocalDate newPostEndDate;
    private String newPostBody;

    public NewPost() {
    }

    public String getNewPostTitle() {
        return newPostTitle;
    }

    public void setNewPostTitle(String newPostTitle) {
        this.newPostTitle = newPostTitle;
    }

    public String getNewPostUserName() {
        return newPostUserName;
    }

    public void setNewPostUserName(String newPostUserName) {
        this.newPostUserName = newPostUserName;
    }

    public LocalDate getNewPostStartDate() {
        return newPostStartDate;
    }

    public void setNewPostStartDate(String newPostStartDate) {
        this.newPostStartDate = LocalDate.parse(newPostStartDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    public LocalDate getNewPostEndDate() {
        return newPostEndDate;
    }

    public void setNewPostEndDate(String newPostEndDate) {
        try {
            this.newPostEndDate = LocalDate.parse(newPostEndDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        } catch (DateTimeParseException e) {

        }

    }

    public String getNewPostBody() {
        return newPostBody;
    }

    public void setNewPostBody(String newPostBody) {
        this.newPostBody = newPostBody;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.newPostTitle);
        hash = 71 * hash + Objects.hashCode(this.newPostUserName);
        hash = 71 * hash + Objects.hashCode(this.newPostStartDate);
        hash = 71 * hash + Objects.hashCode(this.newPostEndDate);
        hash = 71 * hash + Objects.hashCode(this.newPostBody);

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
        final NewPost other = (NewPost) obj;
        if (!Objects.equals(this.newPostTitle, other.newPostTitle)) {
            return false;
        }
        if (!Objects.equals(this.newPostUserName, other.newPostUserName)) {
            return false;
        }
        if (!Objects.equals(this.newPostBody, other.newPostBody)) {
            return false;
        }

        if (!Objects.equals(this.newPostStartDate, other.newPostStartDate)) {
            return false;
        }
        if (!Objects.equals(this.newPostEndDate, other.newPostEndDate)) {
            return false;
        }

        return true;
    }

}
