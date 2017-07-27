/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dto;



/**
 *
 * @author vincentsiciliano
 */
public class AdminEditPost {
    
    private int postId;
    
    private String startDate;


    private String endDate;
    private String title;
    private String postBody;
    private String userId;
    private String userName;
    //private boolean isArchived;
    //private int approvalStatus;
    
    
    private String imageFlag;
    private String titleFlag;
    private String bodyFlag;
    private String startDateFlag;
    private String endDateFlag;
    private String authorFlag;
    //private int categoryFlag;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageFlag() {
        return imageFlag;
    }

    public void setImageFlag(String imageFlag) {
        this.imageFlag = imageFlag;
    }

    public String getTitleFlag() {
        return titleFlag;
    }

    public void setTitleFlag(String titleFlag) {
        this.titleFlag = titleFlag;
    }

    public String getBodyFlag() {
        return bodyFlag;
    }

    public void setBodyFlag(String bodyFlag) {
        this.bodyFlag = bodyFlag;
    }

    public String getStartDateFlag() {
        return startDateFlag;
    }

    public void setStartDateFlag(String startDateFlag) {
        this.startDateFlag = startDateFlag;
    }

    public String getEndDateFlag() {
        return endDateFlag;
    }

    public void setEndDateFlag(String endDateFlag) {
        this.endDateFlag = endDateFlag;
    }

    public String getAuthorFlag() {
        return authorFlag;
    }

    public void setAuthorFlag(String authorFlag) {
        this.authorFlag = authorFlag;
    }



    

    
    
}
