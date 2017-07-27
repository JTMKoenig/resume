/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.service;

import com.sg.metabeingfinder.dao.PowerDao;
import com.sg.metabeingfinder.dto.Power;
import com.sg.metabeingfinder.dto.PowerMetabeing;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jono
 */
public class PowerService {

    PowerDao dao;

    @Inject
    public PowerService(PowerDao dao) {
        this.dao = dao;
    }

    public List<Power> getAllPowers() {
        return dao.getAllPowers();
    }

    public void deletePower(String powerId) {
        dao.deletePower(powerId);
    }

    public void addPower(Power p) {
        dao.addPower(p);
    }

    public Power getOnePower(String powerId) {
        return dao.getOnePower(powerId);
    }

    public void updatePower(Power p) {
        dao.updatePower(p);
    }

    public List<Power> getMetaPowers(List<PowerMetabeing> powerMetas) {
        List<Power> powers = new ArrayList();
        powerMetas.forEach((pm) -> {
            Power power = dao.getOnePower(pm.getPower().getPowerId());
            if (!powers.stream().anyMatch((p -> p.getPowerId().equals(power.getPowerId())))) {
                powers.add(power);
            }

        });
        return powers;
    }

}
