/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.dao;

import com.sg.metabeingfinder.dto.Power;
import java.util.List;

/**
 *
 * @author jono
 */
public interface PowerDao {

    public void addPower(Power power);

    public void deletePower(String powerId);

    public void updatePower(Power power);

    public Power getOnePower(String powerId);

    public List<Power> getAllPowers();

}
