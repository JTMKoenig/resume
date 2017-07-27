/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.service;

import com.sg.metabeingfinder.dao.LocationDao;
import com.sg.metabeingfinder.dao.OrganizationDao;
import com.sg.metabeingfinder.dto.Organization;
import com.sg.metabeingfinder.dto.OrganizationMetabeing;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jono
 */
public class OrganizationService {

    OrganizationDao dao;
    LocationDao lDao;

    @Inject
    public OrganizationService(OrganizationDao dao, LocationDao lDao) {
        this.dao = dao;
        this.lDao = lDao;
    }

    public List<Organization> getAllOrganizations() {
        return dao.getAllOrganizations();
    }

    public List<Organization> getOrganizationsByLocation(String locationId) {
        return dao.getOrganizationsByLocation(locationId);
    }

    public void deleteOrganization(String organizationId) {
        dao.deleteOrganization(organizationId);
    }

    public void addOrganization(Organization o) {
        dao.addOrganization(o);
    }

    public Organization getOneOrganization(String organizationId) {
        return dao.getOneOrganization(organizationId);
    }

    public void updateOrganization(Organization o) {
        dao.updateOrganization(o);
    }

    public List<Organization> getOrganizationByMeta(String metabeingId) {
        return dao.getOrganizationByMeta(metabeingId);
    }

    public List<Organization> getTenOrganizations() {
        return dao.getTenOrganizations();
    }

    public List<Organization> populateLocations(List<Organization> organizations) {
        List<Organization> orgsWithLocations = new ArrayList();
        organizations.forEach((o) -> {
            o.setLocation(lDao.getOneLocation(o.getLocation().getLocationId()));
            orgsWithLocations.add(o);
        });
        return orgsWithLocations;
    }

    public List<Organization> getOrgFromOrgMeta(List<OrganizationMetabeing> omList) {
        List<Organization> oList = new ArrayList();
        omList.forEach((om) -> {
            Organization o = dao.getOneOrganization(om.getOrganization().getOrganizationId());
            oList.add(o);
        });
        return populateLocations(oList);
    }

}
