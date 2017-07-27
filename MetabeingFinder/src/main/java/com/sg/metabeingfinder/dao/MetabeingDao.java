/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.dao;

import com.sg.metabeingfinder.dto.Metabeing;
import com.sg.metabeingfinder.dto.Power;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jono
 */
public interface MetabeingDao {

    public void addMetabeing(Metabeing metabeing);

    public void deleteMetabeing(String metabeingId);

    public void updateMetabeing(Metabeing metabeing);

    public Metabeing getOneMetabeing(String metabeingId);

    public List<Metabeing> getAllMetabeings();

    public List<Metabeing> findMetaByLocation(String locationId);

    public List<Metabeing> findMetaByOrganization(String organizationId);

    public List<Metabeing> getTenMetas();

}
