/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.dao;

import com.sg.metabeingfinder.dto.Sighting;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author jono
 */
public interface SightingDao {

    public void addSighting(Sighting sighting);

    public void deleteSighting(String sightingId);

    public void updateSighting(Sighting sighting);

    public Sighting getOneSighting(String sightingId);

    public List<Sighting> getAllSightings();

    public List<Sighting> getSightingByDate(LocalDate date);

    public List<Sighting> getTenSightings();

    public List<Sighting> getSightingByLocation(String locationId);

}
