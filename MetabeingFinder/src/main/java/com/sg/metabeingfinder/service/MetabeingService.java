/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.service;

import com.sg.metabeingfinder.dao.MetabeingDao;
import com.sg.metabeingfinder.dto.Metabeing;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jono
 */
public class MetabeingService {

    MetabeingDao dao;

    @Inject
    public MetabeingService(MetabeingDao dao) {
        this.dao = dao;
    }

    public List<Metabeing> getAllMetabeings() {
        return dao.getAllMetabeings();
    }

    public void deleteMetabeing(String metabeingId) {
        dao.deleteMetabeing(metabeingId);
    }

    public void addMetabeing(Metabeing m) {
        dao.addMetabeing(m);
    }

    public Metabeing getOneMetabeing(String metabeingId) {
        return dao.getOneMetabeing(metabeingId);
    }

    public void updateMetabeing(Metabeing m) {
        dao.updateMetabeing(m);
    }

    public List<Metabeing> getMetaByLocation(String locationId) {
        return dao.findMetaByLocation(locationId);
    }

    public List<Metabeing> getMetaByOrganization(String organizationId) {
        return dao.findMetaByOrganization(organizationId);
    }

    public List<Metabeing> getTenMetas() {
        return dao.getTenMetas();
    }

}
