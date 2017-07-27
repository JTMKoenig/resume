/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.contactlistspringmvc.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author jono
 */
public class Contact {

    private long contactId;
    @NotEmpty(message = "First Name is required")
    @Length(max = 50, message = "First Name cannot be longer than 50 characters")
    private String firstName;
    @NotEmpty(message = "Last Name is required")
    @Length(max = 50, message = "Last Name cannot be longer than 50 characters")
    private String lastName;
    @NotEmpty(message = "Company is required")
    @Length(max = 50, message = "Company cannot be longer than 50 characters")
    private String company;
    @NotEmpty(message = "Phone is required")
    @Length(max = 10, message = "Phone cannot be longer than 10 characters")
    private String phone;
    @Email(message = "Valid Email is required")
    @Length(max = 50, message = "Email cannot be longer than 50 characters")
    private String email;

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
