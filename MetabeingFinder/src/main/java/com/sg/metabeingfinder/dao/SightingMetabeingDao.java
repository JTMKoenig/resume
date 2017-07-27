/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.dao;

import com.sg.metabeingfinder.dto.Metabeing;
import com.sg.metabeingfinder.dto.Sighting;
import com.sg.metabeingfinder.dto.SightingMetabeing;
import java.util.List;

/**
 *
 * @author jono
 */
public interface SightingMetabeingDao {

    public List<SightingMetabeing> getRecentSightingMetas();

    public void addSightingMeta(SightingMetabeing sightingMeta);

    public void deleteSightingMeta(String sightingMetabeingId);

    public void updateSightingMeta(SightingMetabeing sightingMeta);

    public SightingMetabeing getOneSightingMeta(String sightingMetabeingId);

    public List<SightingMetabeing> getAllSightingMetas();

    public List<SightingMetabeing> getSightMetasByMetaId(String metabeingId);

    public void deleteSightingMetaByMetaId(String metabeingId);

    public List<SightingMetabeing> getSightMetasBySightingId(String sightingId);

}
