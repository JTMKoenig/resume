/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.controller;

import com.sg.metabeingfinder.dto.Metabeing;
import com.sg.metabeingfinder.dto.Organization;
import com.sg.metabeingfinder.dto.OrganizationMetabeing;
import com.sg.metabeingfinder.dto.Power;
import com.sg.metabeingfinder.dto.PowerMetabeing;
import com.sg.metabeingfinder.dto.Sighting;
import com.sg.metabeingfinder.dto.SightingMetabeing;
import com.sg.metabeingfinder.service.MetabeingService;
import com.sg.metabeingfinder.service.OrganizationMetabeingService;
import com.sg.metabeingfinder.service.OrganizationService;
import com.sg.metabeingfinder.service.PowerMetabeingService;
import com.sg.metabeingfinder.service.PowerService;
import com.sg.metabeingfinder.service.SightingMetabeingService;
import com.sg.metabeingfinder.service.SightingService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
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
public class MetabeingController {

    MetabeingService mService;
    PowerService pService;
    PowerMetabeingService pmService;
    OrganizationService oService;
    OrganizationMetabeingService omService;
    SightingService sService;
    SightingMetabeingService smService;

    @Inject
    MetabeingController(MetabeingService mService, PowerService pService,
            PowerMetabeingService pmService, OrganizationService oService,
            OrganizationMetabeingService omService, SightingService sService,
            SightingMetabeingService smService) {
        this.mService = mService;
        this.pService = pService;
        this.pmService = pmService;
        this.oService = oService;
        this.omService = omService;
        this.sService = sService;
        this.smService = smService;
    }

    @RequestMapping(value = "/metabeing", method = RequestMethod.GET)
    public String displayMetabeingPage(Model model) {
        List<Metabeing> metas = mService.getTenMetas();
        List<PowerMetabeing> powerMetas = pmService.getPowerMetaByMetaId(metas);
        List<Power> powers = pService.getMetaPowers(powerMetas);
        Map<Metabeing, List<Power>> metaPowers = pmService.createMap(metas, powers, powerMetas);
        List<Power> allPowers = pService.getAllPowers();
        List<Organization> allOrganizations = oService.getAllOrganizations();

        model.addAttribute("allOrgs", allOrganizations);
        model.addAttribute("metaPowers", metaPowers);
        model.addAttribute("allPowers", allPowers);

        return "metabeing";
    }

    @RequestMapping(value = "/metabeing/show/{id}", method = RequestMethod.GET)
    public String displayMetabeingDetails(@PathVariable("id") String metabeingId, Model model) {
        //get metabeing
        Metabeing m = mService.getOneMetabeing(metabeingId);
        List<Metabeing> mList = new ArrayList();
        mList.add(m);
        //get metabeing powers
        List<PowerMetabeing> pmList = pmService.getPowerMetaByMetaId(mList);
        List<Power> pList = pService.getMetaPowers(pmList);
        //get metabeing organizations
        List<OrganizationMetabeing> omList = omService.getOrgMetasByMetaId(m.getMetabeingId());
        List<Organization> oList = oService.getOrgFromOrgMeta(omList);
        //get metabeing sightings
        List<SightingMetabeing> smList = smService.getSightMetasByMetaId(m.getMetabeingId());
        smList = smService.populateSightingMetas(smList);
        HashMap<Sighting, List<Metabeing>> smMap = smService.createMap(smList);
        //get ALL powers
        List<Power> allPowers = pService.getAllPowers();
        //get ALL organizations
        List<Organization> allOrgs = oService.getAllOrganizations();

        model.addAttribute("allOrgs", allOrgs);
        model.addAttribute("allPowers", allPowers);
        model.addAttribute("sightingMetas", smMap);
        model.addAttribute("powers", pList);
        model.addAttribute("orgs", oList);
        model.addAttribute("meta", m);

        return "metabeingDetails";
    }

    @RequestMapping(value = "/metabeing/add", method = RequestMethod.POST)
    public String addMetabeing(@ModelAttribute Metabeing m, @ModelAttribute Power p,
            @ModelAttribute Organization o) {
        //add new Metabeing
        mService.addMetabeing(m);

        //create and add PowerMetabeing
        PowerMetabeing pm = new PowerMetabeing();
        pm.setPower(pService.getOnePower(p.getPowerId()));
        pm.setMetabeing(m);
        pmService.addPowerMeta(pm);

        //create and add OrganizationMetabeing
        OrganizationMetabeing om = new OrganizationMetabeing();
        om.setOrganization(oService.getOneOrganization(o.getOrganizationId()));
        om.setMetabeing(m);
        omService.addOrgMeta(om);

        return "redirect:/metabeing";
    }

    @RequestMapping(value = "/metabeing/delete/{id}", method = RequestMethod.POST)
    public String deleteMetabeing(@PathVariable("id") String metabeingId) {
        omService.deleteOrgMetaByMetaId(metabeingId);
        pmService.deletePowerMetaByMetaId(metabeingId);
        smService.deleteSightMetaByMetaId(metabeingId);
        mService.deleteMetabeing(metabeingId);

        return "redirect:/metabeing";
    }

    @RequestMapping(value = "/metabeing/update/{id}", method = RequestMethod.POST)
    public String updateMetabeing(@PathVariable("id") String metabeingId, WebRequest request) {
        Metabeing m = mService.getOneMetabeing(metabeingId);
        //DELETE OLD metabeing data
        omService.deleteOrgMetaByMetaId(metabeingId);
        pmService.deletePowerMetaByMetaId(metabeingId);

        //SET NEW metabeing data
        m.setName(request.getParameter("name"));
        m.setDescription(request.getParameter("description"));
        m.setAlias(request.getParameter("alias"));
        //SAVE NEW metabeing data
        mService.updateMetabeing(m);

        //GET Power
        Power p = pService.getOnePower(request.getParameter("powerId"));
        //GET Organization
        Organization o = oService.getOneOrganization(request.getParameter("organizationId"));
        //CREATE new OrganizationMetabeing and PowerMetabeing
        OrganizationMetabeing om = new OrganizationMetabeing();
        om.setOrganization(o);
        om.setMetabeing(m);
        PowerMetabeing pm = new PowerMetabeing();
        pm.setPower(p);
        pm.setMetabeing(m);
        //SAVE om and pm
        omService.addOrgMeta(om);
        pmService.addPowerMeta(pm);

        return "redirect:/metabeing/show/" + metabeingId;
    }

}
