/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.service;

import com.sg.metabeingfinder.dao.OrganizationMetabeingDao;
import com.sg.metabeingfinder.dto.Metabeing;
import com.sg.metabeingfinder.dto.OrganizationMetabeing;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jono
 */
public class OrganizationMetabeingService {

    OrganizationMetabeingDao dao;

    @Inject
    public OrganizationMetabeingService(OrganizationMetabeingDao dao) {
        this.dao = dao;
    }

    public List<OrganizationMetabeing> getAllOrgMetas() {
        return dao.getAllOrgMetas();
    }

    public void deleteOrgMeta(String organizationMetabeingId) {
        dao.deleteOrgMeta(organizationMetabeingId);
    }

    public void addOrgMeta(OrganizationMetabeing om) {
        dao.addOrgMeta(om);
    }

    public OrganizationMetabeing getOneOrgMeta(String organizationMetabeingId) {
        return dao.getOneOrgMeta(organizationMetabeingId);
    }

    public void updateOrgMeta(OrganizationMetabeing om) {
        dao.updateOrgMeta(om);
    }

    public List<OrganizationMetabeing> getOrgMetasByMetaId(String metabeingId) {
        return dao.getOrgMetaByMetaId(metabeingId);
    }

    public void deleteOrgMetaByMetaId(String metabeingId) {
        dao.deleteOrgMetaByMetaId(metabeingId);
    }

    public void deleteOrgMetaByOrgId(String organizationId) {
        dao.deleteOrgMetaByOrgId(organizationId);
    }

}
