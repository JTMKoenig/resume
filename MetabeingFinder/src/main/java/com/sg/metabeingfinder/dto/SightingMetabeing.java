/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.dto;

/**
 *
 * @author jono
 */
public class SightingMetabeing {

    private String sightingMetabeingId;
    private Sighting sighting;
    private Metabeing metabeing;

    public String getSightingMetabeingId() {
        return sightingMetabeingId;
    }

    public void setSightingMetabeingId(String sightingMetabeingId) {
        this.sightingMetabeingId = sightingMetabeingId;
    }

    public Sighting getSighting() {
        return sighting;
    }

    public void setSighting(Sighting sighting) {
        this.sighting = sighting;
    }

    public Metabeing getMetabeing() {
        return metabeing;
    }

    public void setMetabeing(Metabeing metabeing) {
        this.metabeing = metabeing;
    }

}
