/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.dto;

import java.util.List;

/**
 *
 * @author jono
 */
public class PowerMetabeing {

    private String powerMetabeingId;
    private Power power;
    private Metabeing metabeing;

    public String getPowerMetabeingId() {
        return powerMetabeingId;
    }

    public void setPowerMetabeingId(String powerMetabeingId) {
        this.powerMetabeingId = powerMetabeingId;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    public Metabeing getMetabeing() {
        return metabeing;
    }

    public void setMetabeing(Metabeing metabeing) {
        this.metabeing = metabeing;
    }

}
