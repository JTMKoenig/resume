/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.dao;

import com.sg.metabeingfinder.dto.Location;
import java.util.List;

/**
 *
 * @author jono
 */
public interface LocationDao {

    public void addLocation(Location location);

    public void deleteLocation(String locationId);

    public void updateLocation(Location location);

    public Location getOneLocation(String locationId);

    public List<Location> getAllLocations();

    public List<Location> getLocationByMetabeing(String metabeingId);

    public List<Location> getTenLocations();

}
