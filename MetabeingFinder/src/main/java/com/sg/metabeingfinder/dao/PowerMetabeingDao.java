/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.dao;

import com.sg.metabeingfinder.dto.Metabeing;
import com.sg.metabeingfinder.dto.Power;
import com.sg.metabeingfinder.dto.PowerMetabeing;
import java.util.List;

/**
 *
 * @author jono
 */
public interface PowerMetabeingDao {

    public void addPowerMeta(PowerMetabeing powerMeta);

    public void deletePowerMeta(String powerMetaId);

    public void updatePowerMeta(PowerMetabeing powerMeta);

    public PowerMetabeing getOnePowerMeta(String powerMetabeingId);

    public List<PowerMetabeing> getAllPowerMetas();

    public PowerMetabeing getPowersByMetaId(String metabeingId);

    public void deletePowerMetaByMetaId(String metabeingId);

}
