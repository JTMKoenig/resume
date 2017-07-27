/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.service;

import com.sg.metabeingfinder.dao.LocationDao;
import com.sg.metabeingfinder.dto.Location;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jono
 */
public class LocationService {

    LocationDao locationDao;

    @Inject
    public LocationService(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    public List<Location> getAllLocations() {
        return locationDao.getAllLocations();
    }

    public void deleteLocation(String locationId) {
        locationDao.deleteLocation(locationId);
    }

    public Location getOneLocation(String locationId) {
        return locationDao.getOneLocation(locationId);
    }

    public void updateLocation(Location l) {
        locationDao.updateLocation(l);
    }

    public void addLocation(Location l) {
        locationDao.addLocation(l);
    }

    public List<Location> getLocationByMetabeing(String metabeingId) {
        return locationDao.getLocationByMetabeing(metabeingId);
    }

    public List<Location> getTenLocations() {
        return locationDao.getTenLocations();
    }

}
