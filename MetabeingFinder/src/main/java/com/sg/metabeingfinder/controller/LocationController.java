/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.controller;

import com.sg.metabeingfinder.dto.Location;
import com.sg.metabeingfinder.dto.Metabeing;
import com.sg.metabeingfinder.dto.Organization;
import com.sg.metabeingfinder.dto.Sighting;
import com.sg.metabeingfinder.dto.SightingMetabeing;
import com.sg.metabeingfinder.service.LocationService;
import com.sg.metabeingfinder.service.OrganizationService;
import com.sg.metabeingfinder.service.SightingMetabeingService;
import com.sg.metabeingfinder.service.SightingService;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class LocationController {

    LocationService lService;
    OrganizationService oService;
    SightingService sService;
    SightingMetabeingService smService;

    @Inject
    public LocationController(LocationService lService,
            OrganizationService oService, SightingService sService,
            SightingMetabeingService smService) {
        this.lService = lService;
        this.oService = oService;
        this.sService = sService;
        this.smService = smService;
    }

    @RequestMapping(value = "/location", method = RequestMethod.GET)
    public String displayLocationPage(Model model) {
        List<Location> locations = lService.getTenLocations();

        model.addAttribute("locations", locations);

        return "location";
    }

    @RequestMapping(value = "/location/show/{id}", method = RequestMethod.GET)
    public String displayLocationDetails(@PathVariable("id") String locationId, Model model) {
        Location l = lService.getOneLocation(locationId);
        //get Orgs for Location
        List<Organization> orgs = oService.getOrganizationsByLocation(locationId);
        orgs = oService.populateLocations(orgs);
        //get Sightings for Location
        List<Sighting> sightings = sService.getSightingByLocation(locationId);
        List<SightingMetabeing> smList = smService.getSightMetasBySightingId(sightings);
        smList = smService.populateSightingMetas(smList);
        HashMap<Sighting, List<Metabeing>> sightingMetaMap = smService.createMap(smList);

        model.addAttribute("sightingMetaMap", sightingMetaMap);
        model.addAttribute("orgs", orgs);
        model.addAttribute("loc", l);

        return "locationDetails";
    }

    @RequestMapping(value = "/location/add", method = RequestMethod.POST)
    public String addLocation(@ModelAttribute Location l) {
        lService.addLocation(l);

        return "redirect:/location";
    }

    @RequestMapping(value = "/location/update/{id}", method = RequestMethod.POST)
    public String updateLocation(@PathVariable("id") String locationId, WebRequest request) {
        Location l = lService.getOneLocation(locationId);
        l.setStreetAddress(request.getParameter("streetAddress"));
        l.setCity(request.getParameter("city"));
        l.setState(request.getParameter("state"));
        l.setCountry(request.getParameter("country"));
        l.setLatitude(request.getParameter("latitude"));
        l.setLongitude(request.getParameter("longitude"));
        l.setDescription(request.getParameter("description"));
        lService.updateLocation(l);

        return "redirect:/location/show/" + locationId;
    }

    @RequestMapping(value = "/location/delete/{id}", method = RequestMethod.POST)
    public String updateLocation(@PathVariable("id") String locationId) {
        lService.deleteLocation(locationId);

        return "redirect:/location";
    }

}
