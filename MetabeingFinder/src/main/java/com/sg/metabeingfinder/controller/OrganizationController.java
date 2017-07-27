/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.controller;

import com.sg.metabeingfinder.dto.Location;
import com.sg.metabeingfinder.dto.Metabeing;
import com.sg.metabeingfinder.dto.Organization;
import com.sg.metabeingfinder.dto.OrganizationMetabeing;
import com.sg.metabeingfinder.dto.Power;
import com.sg.metabeingfinder.dto.PowerMetabeing;
import com.sg.metabeingfinder.service.LocationService;
import com.sg.metabeingfinder.service.MetabeingService;
import com.sg.metabeingfinder.service.OrganizationMetabeingService;
import com.sg.metabeingfinder.service.OrganizationService;
import com.sg.metabeingfinder.service.PowerMetabeingService;
import com.sg.metabeingfinder.service.PowerService;
import java.util.List;
import java.util.Map;
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
public class OrganizationController {
    
    OrganizationService oService;
    LocationService lService;
    MetabeingService mService;
    PowerService pService;
    PowerMetabeingService pmService;
    OrganizationMetabeingService omService;
    
    @Inject
    public OrganizationController(OrganizationService oService,
            LocationService lService, MetabeingService mService,
            PowerService pService, PowerMetabeingService pmService,
            OrganizationMetabeingService omService) {
        this.oService = oService;
        this.lService = lService;
        this.mService = mService;
        this.pService = pService;
        this.pmService = pmService;
        this.omService = omService;
    }
    
    @RequestMapping(value = "/organization", method = RequestMethod.GET)
    public String displayOrganizationPage(Model model) {
        List<Organization> organizations = oService.getTenOrganizations();
        organizations = oService.populateLocations(organizations);
        List<Location> allLocations = lService.getAllLocations();
        
        model.addAttribute("locations", allLocations);
        model.addAttribute("organizations", organizations);
        
        return "organization";
    }
    
    @RequestMapping(value = "/organization/add", method = RequestMethod.POST)
    public String addNewOrganization(@ModelAttribute Organization o, @RequestParam("locationSelection") String locationId) {
        //populate Organization Location
        o.setLocation(lService.getOneLocation(locationId));
        //add new Organization to DB
        oService.addOrganization(o);
        
        return "redirect:/organization";
    }

    @RequestMapping(value = "/organization/delete/{id}", method = RequestMethod.POST)
    public String deleteOrganization(@PathVariable("id") String organizationId) {
        Organization o = oService.getOneOrganization(organizationId);
        //delete OM Bridge Rows
        omService.deleteOrgMetaByOrgId(organizationId);
        //delete Org
        oService.deleteOrganization(organizationId);

        return "redirect:/organization";
    }
    
    @RequestMapping(value = "/organization/show/{id}", method = RequestMethod.GET)
    public String showOrganizationDetails(@PathVariable("id") String organizationId, Model model) {
        //get org and set location
        Organization o = oService.getOneOrganization(organizationId);
        o.setLocation(lService.getOneLocation(o.getLocation().getLocationId()));
        //get org members and powers
        List<Metabeing> metas = mService.getMetaByOrganization(organizationId);
        List<PowerMetabeing> pmList = pmService.getPowerMetaByMetaId(metas);
        List<Power> powers = pService.getMetaPowers(pmList);
        Map<Metabeing, List<Power>> metaPowers = pmService.createMap(metas, powers, pmList);
        //get all locations
        List<Location> locations = lService.getAllLocations();
        
        model.addAttribute("metaPowers", metaPowers);
        model.addAttribute("metas", metas);
        model.addAttribute("locations", locations);
        model.addAttribute("org", o);
        
        return "organizationDetails";
    }
    
    @RequestMapping(value = "/organization/update/{id}", method = RequestMethod.POST)
    public String updateOrganization(@PathVariable("id") String organizationId, WebRequest request) {
        //get org and set values
        Organization o = oService.getOneOrganization(organizationId);
        o.setLocation(lService.getOneLocation(request.getParameter("locationId")));
        o.setName(request.getParameter("name"));
        o.setDescription(request.getParameter("description"));
        o.setPhone(request.getParameter("phone"));
        String email = request.getParameter("email");
        o.setEmail(email);
        //update org
        oService.updateOrganization(o);
        
        return "redirect:/organization/show/" + organizationId;
    }
    
}
