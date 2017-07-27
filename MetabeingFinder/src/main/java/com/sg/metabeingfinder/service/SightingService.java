/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.service;

import com.sg.metabeingfinder.dao.LocationDao;
import com.sg.metabeingfinder.dao.SightingDao;
import com.sg.metabeingfinder.dto.Sighting;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jono
 */
public class SightingService {

    SightingDao dao;
    LocationDao lDao;

    @Inject
    public SightingService(SightingDao dao, LocationDao lDao) {
        this.dao = dao;
        this.lDao = lDao;
    }

    public List<Sighting> getAllSightings() {
        return dao.getAllSightings();
    }

    public void deleteSighting(String sightingId) {
        dao.deleteSighting(sightingId);
    }

    public void addSighting(Sighting s) {
        dao.addSighting(s);
    }

    public Sighting getOneSighting(String sightingId) {
        return dao.getOneSighting(sightingId);
    }

    public void updateSighting(Sighting s) {
        dao.updateSighting(s);
    }

    public List<Sighting> getTenSightings() {
        return dao.getTenSightings();
    }

    public List<Sighting> populateSightings(List<Sighting> sightings) {
        List<Sighting> sightingWithLocation = new ArrayList();
        sightings.forEach((s) -> {
            s.setLocation(lDao.getOneLocation(s.getLocation().getLocationId()));
            sightingWithLocation.add(s);
        });
        return sightingWithLocation;
    }

    public List<Sighting> getSightingByLocation(String locationId) {
        return dao.getSightingByLocation(locationId);
    }

}
