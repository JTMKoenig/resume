/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.dao;

import com.sg.metabeingfinder.dto.Metabeing;
import com.sg.metabeingfinder.dto.Organization;
import com.sg.metabeingfinder.dto.OrganizationMetabeing;
import java.util.List;

/**
 *
 * @author jono
 */
public interface OrganizationMetabeingDao {

    public void addOrgMeta(OrganizationMetabeing orgMeta);

    public void deleteOrgMeta(String organizationMetabeingId);

    public void updateOrgMeta(OrganizationMetabeing orgMeta);

    public OrganizationMetabeing getOneOrgMeta(String organizationMetabeingId);

    public List<OrganizationMetabeing> getAllOrgMetas();

    public List<OrganizationMetabeing> getOrgMetaByMetaId(String metabeingId);

    public void deleteOrgMetaByMetaId(String metabeingId);

    public void deleteOrgMetaByOrgId(String organizationId);

}
