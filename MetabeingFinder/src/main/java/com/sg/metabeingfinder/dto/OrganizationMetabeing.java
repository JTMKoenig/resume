/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.dto;

/**
 *
 * @author jono
 */
public class OrganizationMetabeing {

    private String organizationMetabeingId;
    private Organization organization;
    private Metabeing metabeing;

    public String getOrganizationMetabeingId() {
        return organizationMetabeingId;
    }

    public void setOrganizationMetabeingId(String organizationMetabeingId) {
        this.organizationMetabeingId = organizationMetabeingId;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Metabeing getMetabeing() {
        return metabeing;
    }

    public void setMetabeing(Metabeing metabeing) {
        this.metabeing = metabeing;
    }

}
