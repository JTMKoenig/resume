/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author vincentsiciliano
 */
public class Post {
    
    private int postId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private String postBody;
    private int userId; 
    private User user;
    private boolean isArchived;
    private int approvalStatus;
    
    private String colorStatus;

    
    private int imageFlag;
    private int titleFlag;
    private int bodyFlag;
    private int startDateFlag;
    private int endDateFlag;
    private int authorFlag;
    private int categoryFlag;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
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

    public boolean isIsArchived() {
        return isArchived;
    }

    public void setIsArchived(boolean isArchived) {
        this.isArchived = isArchived;
    }

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public int getImageFlag() {
        return imageFlag;
    }

    public void setImageFlag(int imageFlag) {
        this.imageFlag = imageFlag;
    }

    public int getTitleFlag() {
        return titleFlag;
    }

    public void setTitleFlag(int titleFlag) {
        this.titleFlag = titleFlag;
    }

    public int getBodyFlag() {
        return bodyFlag;
    }

    public void setBodyFlag(int bodyFlag) {
        this.bodyFlag = bodyFlag;
    }

    public int getStartDateFlag() {
        return startDateFlag;
    }

    public void setStartDateFlag(int startDateFlag) {
        this.startDateFlag = startDateFlag;
    }

    public int getEndDateFlag() {
        return endDateFlag;
    }

    public void setEndDateFlag(int endDateFlag) {
        this.endDateFlag = endDateFlag;
    }

    public int getAuthorFlag() {
        return authorFlag;
    }

    public void setAuthorFlag(int authorFlag) {
        this.authorFlag = authorFlag;
    }

    public int getCategoryFlag() {
        return categoryFlag;
    }

    public void setCategoryFlag(int categoryFlag) {
        this.categoryFlag = categoryFlag;
    }



    public String getColorStatus() {
        return colorStatus;
    }

    public void setColorStatus(String colorStatus) {
        this.colorStatus = colorStatus;
    }
    
    


    
    
    
    
    
    
    
   
    
    


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.postId;
        hash = 89 * hash + Objects.hashCode(this.startDate);
        hash = 89 * hash + Objects.hashCode(this.endDate);
        hash = 89 * hash + Objects.hashCode(this.title);
        hash = 89 * hash + Objects.hashCode(this.postBody);
        hash = 89 * hash + this.userId;
        hash = 89 * hash + Objects.hashCode(this.user);
        hash = 89 * hash + (this.isArchived ? 1 : 0);
        hash = 89 * hash + this.approvalStatus;
        hash = 89 * hash + this.imageFlag;
        hash = 89 * hash + this.titleFlag;
        hash = 89 * hash + this.bodyFlag;
        hash = 89 * hash + this.startDateFlag;
        hash = 89 * hash + this.endDateFlag;
        hash = 89 * hash + this.authorFlag;
        hash = 89 * hash + this.categoryFlag;
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
        final Post other = (Post) obj;
        if (this.postId != other.postId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (this.isArchived != other.isArchived) {
            return false;
        }
        if (this.approvalStatus != other.approvalStatus) {
            return false;
        }
        if (this.imageFlag != other.imageFlag) {
            return false;
        }
        if (this.titleFlag != other.titleFlag) {
            return false;
        }
        if (this.bodyFlag != other.bodyFlag) {
            return false;
        }
        if (this.startDateFlag != other.startDateFlag) {
            return false;
        }
        if (this.endDateFlag != other.endDateFlag) {
            return false;
        }
        if (this.authorFlag != other.authorFlag) {
            return false;
        }
        if (this.categoryFlag != other.categoryFlag) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.postBody, other.postBody)) {
            return false;
        }
        if (!(this.startDate.compareTo(other.startDate) == 0)) {
            return false;
        }
        if (!(this.endDate.compareTo(other.endDate) == 0)) {
            return false;
        }
        if (!this.user.equals(other.user)) {
            return false;
        }
        return true;
    }

    
    
    
}
