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
public class StaticPage {
    
    private int staticPageId;
    private int staticPageIndex;
    private String staticPageTitle;
    private String StaticPageBody;
    private String colorStatus;
    private boolean isArchived;

    public StaticPage() {
    }
    
    

    public int getStaticPageId() {
        return staticPageId;
    }

    public void setStaticPageId(int staticPageId) {
        this.staticPageId = staticPageId;
    }

    public int getStaticPageIndex() {
        return staticPageIndex;
    }

    public void setStaticPageIndex(int staticPageIndex) {
        this.staticPageIndex = staticPageIndex;
    }




    public String getStaticPageTitle() {
        return staticPageTitle;
    }

    public void setStaticPageTitle(String staticPageTitle) {
        this.staticPageTitle = staticPageTitle;
    }

    public String getStaticPageBody() {
        return StaticPageBody;
    }

    public void setStaticPageBody(String StaticPageBody) {
        this.StaticPageBody = StaticPageBody;
    }

    public boolean isIsArchived() {
        return isArchived;
    }

    public void setIsArchived(boolean isArchived) {
        this.isArchived = isArchived;
    }

    public String getColorStatus() {
        return colorStatus;
    }

    public void setColorStatus(String colorStatus) {
        this.colorStatus = colorStatus;
    }

    

    
    
    
}
