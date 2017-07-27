/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.service;

import com.sg.metabeingfinder.dao.PowerMetabeingDao;
import com.sg.metabeingfinder.dto.Metabeing;
import com.sg.metabeingfinder.dto.Power;
import com.sg.metabeingfinder.dto.PowerMetabeing;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author jono
 */
public class PowerMetabeingService {

    PowerMetabeingDao dao;

    @Inject
    public PowerMetabeingService(PowerMetabeingDao dao) {
        this.dao = dao;
    }

    public List<PowerMetabeing> getAllPowerMetas() {
        return dao.getAllPowerMetas();
    }

    public void deletePowerMeta(String powerMetabeingId) {
        dao.deletePowerMeta(powerMetabeingId);
    }

    public void deletePowerMetaByMetaId(String metabeingId) {
        dao.deletePowerMetaByMetaId(metabeingId);
    }

    public void addPowerMeta(PowerMetabeing pm) {
        dao.addPowerMeta(pm);
    }

    public PowerMetabeing getOnePowerMeta(String powerMetabeingId) {
        return dao.getOnePowerMeta(powerMetabeingId);
    }

    public void updatePowerMetabeing(PowerMetabeing pm) {
        dao.updatePowerMeta(pm);
    }

    public List<PowerMetabeing> getPowerMetaByMetaId(List<Metabeing> metas) {
        List<PowerMetabeing> powerMetas = new ArrayList();
        metas.forEach((meta) -> {
            PowerMetabeing pm = dao.getPowersByMetaId(meta.getMetabeingId());
            powerMetas.add(pm);
        });
        return powerMetas;
    }

    public Map<Metabeing, List<Power>> createMap(List<Metabeing> metas,
            List<Power> powers, List<PowerMetabeing> powerMetas) {
        Map<Metabeing, List<Power>> result = new HashMap();
        for (PowerMetabeing pm : powerMetas) {
            for (Metabeing meta : metas) {
                List<Power> thisMetasPowers = new ArrayList();
                if (meta.getMetabeingId().equals(pm.getMetabeing().getMetabeingId())) {
                    powers.forEach((p) -> {
                        if (p.getPowerId().equals(pm.getPower().getPowerId())) {
                            thisMetasPowers.add(p);
                        }
                    });
                    if (!result.keySet().stream().anyMatch((m -> m.getMetabeingId().equals(meta.getMetabeingId())))) {
                        result.put(meta, thisMetasPowers);
                    }

                }

            }
        }
        return result;
    }

}
