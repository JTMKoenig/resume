/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.controller;

import com.sg.metabeingfinder.dto.Location;
import com.sg.metabeingfinder.dto.Metabeing;
import com.sg.metabeingfinder.dto.Sighting;
import com.sg.metabeingfinder.dto.SightingMetabeing;
import com.sg.metabeingfinder.service.LocationService;
import com.sg.metabeingfinder.service.MetabeingService;
import com.sg.metabeingfinder.service.SightingMetabeingService;
import com.sg.metabeingfinder.service.SightingService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author jono
 */
@Controller
public class SightingController {
    
    SightingMetabeingService smService;
    LocationService lService;
    MetabeingService mService;
    SightingService sService;
    
    @Inject
    public SightingController(SightingMetabeingService smService,
            LocationService lService, MetabeingService mService,
            SightingService sService) {
        this.smService = smService;
        this.lService = lService;
        this.mService = mService;
        this.sService = sService;
    }
    
    @RequestMapping(value = "/sighting", method = RequestMethod.GET)
    public String displaySightingPage(Model model) {
        List<SightingMetabeing> sightingMetas = smService.getRecentSightingMetas();
        sightingMetas = smService.populateSightingMetas(sightingMetas);
        HashMap<Sighting, List<Metabeing>> sightingMetaMap = smService.createMap(sightingMetas);
        List<Location> allLocations = lService.getAllLocations();
        List<Metabeing> allMetas = mService.getAllMetabeings();
        
        model.addAttribute("metas", allMetas);
        model.addAttribute("locations", allLocations);
        model.addAttribute("sightingMetas", sightingMetaMap);
        
        return "sighting";
    }
    
    @RequestMapping(value = "/sighting/add", method = RequestMethod.POST)
    public String addNewSighting(@RequestParam("date") String date,
            @ModelAttribute Location l, @ModelAttribute Metabeing m, @RequestParam("description") String sightDesc) {
        //create sighting and set location
        Sighting s = new Sighting();
        s.setLocation(lService.getOneLocation(l.getLocationId()));
        //create and set date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        s.setDate(localDate);
        //set description
        s.setDescription(sightDesc);
        //add sighting
        sService.addSighting(s);
        //created and add SightingMetabeing
        SightingMetabeing sm = new SightingMetabeing();
        sm.setSighting(s);
        sm.setMetabeing(mService.getOneMetabeing(m.getMetabeingId()));
        smService.addSightingMetabeing(sm);
        
        return "redirect:/sighting";
    }
    
    @RequestMapping(value = "/sighting/show/{id}", method = RequestMethod.GET)
    public String showSightingDetails(@PathVariable("id") String sightingId, Model model) {
        //get sighting and add to list
        List<Sighting> sightings = new ArrayList();
        Sighting s = sService.getOneSighting(sightingId);
        sightings.add(s);
        //populate location
        s.setLocation(lService.getOneLocation(s.getLocation().getLocationId()));
        //get metas at sighting
        List<SightingMetabeing> smList = smService.getSightMetasBySightingId(sightings);
        smList = smService.populateSightingMetas(smList);
        List<Metabeing> metasAtSighting = new ArrayList();
        for (SightingMetabeing sm : smList) {
            Metabeing m = mService.getOneMetabeing(sm.getMetabeing().getMetabeingId());
            metasAtSighting.add(m);
        }
        //set sighting date
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = s.getDate().format(format);
        //get all metas and locations
        List<Metabeing> allMetas = mService.getAllMetabeings();
        List<Location> allLocations = lService.getAllLocations();
        
        model.addAttribute("locations", allLocations);
        model.addAttribute("date", date);
        model.addAttribute("allMetas", allMetas);
        model.addAttribute("metas", metasAtSighting);
        model.addAttribute("sight", s);
        
        return "sightingDetails";
    }
    
    @RequestMapping(value = "/sighting/update/{id}", method = RequestMethod.POST)
    public String updateSighting(@PathVariable("id") String sightingId, WebRequest request) {
        List<Sighting> sightings = new ArrayList();
        //get sighting
        Sighting s = sService.getOneSighting(sightingId);
        sightings.add(s);
        //set description, date and location
        s.setDescription(request.getParameter("description"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localDate = LocalDate.parse(request.getParameter("date"), formatter);
        s.setDate(localDate);
        s.setLocation(lService.getOneLocation(request.getParameter("locationId")));
        //update sighting
        sService.updateSighting(s);
        //delete old sm bridges
        List<SightingMetabeing> oldSM = smService.getSightMetasBySightingId(sightings);
        oldSM.forEach((sm) -> {
            smService.deleteSightingMeta(sm.getSightingMetabeingId());
        });
        //create new sm bridges
        String[] metasAtSighting = request.getParameterValues("metabeingId");
        for (String metaId : metasAtSighting) {
            SightingMetabeing sm = new SightingMetabeing();
            sm.setSighting(s);
            sm.setMetabeing(mService.getOneMetabeing(metaId));
            smService.addSightingMetabeing(sm);
        }
        
        return "redirect:/sighting/show/" + sightingId;
    }
    
    @RequestMapping(value = "/sighting/delete/{id}", method = RequestMethod.POST)
    public String deleteSighting(@PathVariable("id") String sightingId) {
        sService.deleteSighting(sightingId);
        
        return "redirect:/sighting";
    }
    
}
