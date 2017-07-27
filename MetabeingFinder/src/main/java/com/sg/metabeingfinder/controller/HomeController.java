/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.controller;

import com.sg.metabeingfinder.dto.Metabeing;
import com.sg.metabeingfinder.dto.Sighting;
import com.sg.metabeingfinder.dto.SightingMetabeing;
import com.sg.metabeingfinder.service.SightingMetabeingService;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author jono
 */
@Controller
public class HomeController {

    SightingMetabeingService smService;

    @Inject
    HomeController(SightingMetabeingService smService) {
        this.smService = smService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayHomePage(Model model) {
        List<SightingMetabeing> sightingMetas = smService.getRecentSightingMetas();
        sightingMetas = smService.populateSightingMetas(sightingMetas);
        HashMap<Sighting, List<Metabeing>> sightingMetaMap = smService.createMap(sightingMetas);

        model.addAttribute("sightingMetas", sightingMetaMap);

        return "home";
    }

}
