/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.dao;

import com.sg.metabeingfinder.dto.Organization;
import java.util.List;

/**
 *
 * @author jono
 */
public interface OrganizationDao {

    public void addOrganization(Organization organization);

    public void deleteOrganization(String organizationId);

    public void updateOrganization(Organization organization);

    public Organization getOneOrganization(String organizationId);

    public List<Organization> getAllOrganizations();

    public List<Organization> getOrganizationByMeta(String metabeingId);

    public List<Organization> getTenOrganizations();

    public List<Organization> getOrganizationsByLocation(String locationId);

}
