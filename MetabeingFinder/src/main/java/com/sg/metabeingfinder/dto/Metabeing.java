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
public class Metabeing {

    private String metabeingId;
    private String name;
    private String alias;
    private String description;

    public String getMetabeingId() {
        return metabeingId;
    }

    public void setMetabeingId(String metabeingId) {
        this.metabeingId = metabeingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
