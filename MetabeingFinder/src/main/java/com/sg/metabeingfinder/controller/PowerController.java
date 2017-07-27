/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.controller;

import com.sg.metabeingfinder.dto.Location;
import com.sg.metabeingfinder.dto.Power;
import com.sg.metabeingfinder.service.LocationService;
import com.sg.metabeingfinder.service.OrganizationService;
import com.sg.metabeingfinder.service.PowerService;
import com.sg.metabeingfinder.service.SightingMetabeingService;
import com.sg.metabeingfinder.service.SightingService;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author jono
 */
@Controller
public class PowerController {

    PowerService pService;
    LocationService lService;
    OrganizationService oService;
    SightingService sService;
    SightingMetabeingService smService;

    @Inject
    public PowerController(PowerService pService, LocationService lService,
            OrganizationService oService, SightingService sService,
            SightingMetabeingService smService) {
        this.pService = pService;
        this.lService = lService;
        this.oService = oService;
        this.sService = sService;
        this.smService = smService;
    }

    @RequestMapping(value = "/power/add", method = RequestMethod.POST)
    public String addLocation(@ModelAttribute Power p) {
        pService.addPower(p);

        return "redirect:/metabeing";
    }

}
