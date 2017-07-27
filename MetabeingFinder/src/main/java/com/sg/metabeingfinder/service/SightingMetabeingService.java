/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.service;

import com.sg.metabeingfinder.dao.LocationDao;
import com.sg.metabeingfinder.dao.MetabeingDao;
import com.sg.metabeingfinder.dao.SightingDao;
import com.sg.metabeingfinder.dao.SightingMetabeingDao;
import com.sg.metabeingfinder.dto.Metabeing;
import com.sg.metabeingfinder.dto.Sighting;
import com.sg.metabeingfinder.dto.SightingMetabeing;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jono
 */
public class SightingMetabeingService {

    SightingMetabeingDao dao;
    SightingDao sDao;
    MetabeingDao mDao;
    LocationDao lDao;

    @Inject
    public SightingMetabeingService(SightingMetabeingDao dao, SightingDao sDao,
            MetabeingDao mDao, LocationDao lDao) {
        this.dao = dao;
        this.sDao = sDao;
        this.mDao = mDao;
        this.lDao = lDao;
    }

    public List<SightingMetabeing> getAllSightingMetas() {
        return dao.getAllSightingMetas();
    }

    public void deleteSightingMeta(String sightingMetabeingId) {
        dao.deleteSightingMeta(sightingMetabeingId);
    }

    public void deleteSightMetaByMetaId(String metabeingId) {
        dao.deleteSightingMetaByMetaId(metabeingId);
    }

    public void addSightingMetabeing(SightingMetabeing sm) {
        dao.addSightingMeta(sm);
    }

    public SightingMetabeing getOneSightingMetabeing(String sightingMetabeingId) {
        return dao.getOneSightingMeta(sightingMetabeingId);
    }

    public void updateSightingMetabeing(SightingMetabeing sm) {
        dao.updateSightingMeta(sm);
    }

    public List<SightingMetabeing> populateSightingMetas(List<SightingMetabeing> sightingMetas) {
        sightingMetas.forEach((sm) -> {
            sm.setSighting(sDao.getOneSighting(sm.getSighting().getSightingId()));
            sm.getSighting().setLocation(lDao.getOneLocation(sm.getSighting().getLocation().getLocationId()));
            sm.setMetabeing(mDao.getOneMetabeing(sm.getMetabeing().getMetabeingId()));
        });
        return sightingMetas;
    }

    public HashMap<Sighting, List<Metabeing>> createMap(List<SightingMetabeing> sightingMetas) {

        List<Sighting> sightings = new ArrayList();

        sightingMetas.forEach((sm) -> {
            sightings.add(sm.getSighting());
        });

        HashMap<Sighting, List<Metabeing>> result = new HashMap();

        sightings.forEach((sighting) -> {
            List<Metabeing> thisSightingList = new ArrayList();
            sightingMetas.stream().filter((sm) -> (sm.getSighting().getSightingId().equals(sighting.getSightingId()))).forEachOrdered((sm) -> {
                thisSightingList.add(sm.getMetabeing());
            });

            if (!result.keySet().stream().anyMatch(s -> s.getSightingId().equals(sighting.getSightingId()))) {
                result.put(sighting, thisSightingList);
            }

        });
        return result;
    }

    public List<SightingMetabeing> getRecentSightingMetas() {
        return dao.getRecentSightingMetas();
    }

    public List<SightingMetabeing> getSightMetasByMetaId(String metabeingId) {
        return dao.getSightMetasByMetaId(metabeingId);
    }

    public List<SightingMetabeing> getSightMetasBySightingId(List<Sighting> sightings) {
        List<SightingMetabeing> result = new ArrayList();
        for (Sighting s : sightings) {
            List<SightingMetabeing> smList = dao.getSightMetasBySightingId(s.getSightingId());
            for (SightingMetabeing sm1 : smList) {
                result.add(sm1);
            }

        }
        return result;
    }

}
