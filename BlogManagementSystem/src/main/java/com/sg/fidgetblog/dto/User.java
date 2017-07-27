
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dto;

import java.util.ArrayList;
import java.util.Objects;

//ROLE_ADMIN
//ROLE_USER
//ROLE_CONTENTMANAGER
/**
 *
 * @author vincentsiciliano
 */
public class User {

    private int userId;
    private String userName;
    private String passWord;
    private boolean isActive;
    private boolean isBanned;
    private ArrayList<String> authorities = new ArrayList<>();

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public ArrayList<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(ArrayList<String> authorities) {
        this.authorities = authorities;
    }

    public void addAuthority(String authority) {
        authorities.add(authority);
    }

    public boolean getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(boolean isBanned) {
        this.isBanned = isBanned;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.userId;
        hash = 43 * hash + Objects.hashCode(this.userName);
        hash = 43 * hash + Objects.hashCode(this.passWord);
        hash = 43 * hash + (this.isActive ? 1 : 0);
        hash = 43 * hash + (this.isBanned ? 1 : 0);
        hash = 43 * hash + Objects.hashCode(this.authorities);
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
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.isActive != other.isActive) {
            return false;
        }
        if (this.isBanned != other.isBanned) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.passWord, other.passWord)) {
            return false;
        }
        if (!Objects.equals(this.authorities, other.authorities)) {
            return false;
        }
        return true;
    }

}
