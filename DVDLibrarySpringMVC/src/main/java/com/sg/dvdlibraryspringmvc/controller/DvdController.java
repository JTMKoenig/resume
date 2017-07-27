/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.controller;

import com.sg.dvdlibraryspringmvc.dao.SearchTerm;
import com.sg.dvdlibraryspringmvc.model.Dvd;
import com.sg.dvdlibraryspringmvc.services.DvdService;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author jono
 */
@Controller
public class DvdController {

    DvdService dvdService;

    @Inject
    public DvdController(DvdService dvdService) {
        this.dvdService = dvdService;
    }

    @RequestMapping(value = "/addDvd", method = RequestMethod.POST)
    public String add(@ModelAttribute Dvd dvd) {
        Dvd addedDvd = dvdService.add(dvd);

        return "redirect:/";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("dvd") Dvd dvd) {
        dvdService.update(dvd);

        return "redirect:/";
    }

    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Dvd getDvd(@PathVariable("id") int id) {
        return dvdService.read(id);

    }

    @RequestMapping(value = "/dvd/delete/{id}")
    public String update(@PathVariable("id") int id) {
        dvdService.delete(id);

        return "redirect:/";
    }

    @RequestMapping(value = "/dvd/show/{id}")
    @ResponseBody
    public Dvd showDvd(@PathVariable("id") int id) {
        return dvdService.read(id);

    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchDvds(@RequestParam("searchCategory") String category, @RequestParam("searchTerm") String searchTerm, Model model) {
        Map<SearchTerm, String> criteriaMap = new HashMap();

        SearchTerm type = null;

        if (category.equals("title")) {
            type = SearchTerm.TITLE;
        }
        if (category.equals("year")) {
            type = SearchTerm.RELEASE_DATE;
        }
        if (category.equals("director")) {
            type = SearchTerm.DIRECTOR;
        }
        if (category.equals("rating")) {
            type = SearchTerm.RATING;
        }

        if (searchTerm != null && !searchTerm.isEmpty()) {
            criteriaMap.put(type, searchTerm);
        }

        List<Dvd> dvds = dvdService.searchDvds(criteriaMap);
        model.addAttribute("dvdList", dvds);

        return "library";
    }
}
