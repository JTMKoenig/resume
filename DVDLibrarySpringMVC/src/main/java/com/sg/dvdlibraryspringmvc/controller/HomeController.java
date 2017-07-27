/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.controller;

import com.sg.dvdlibraryspringmvc.model.Dvd;
import com.sg.dvdlibraryspringmvc.services.DvdService;
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

    DvdService dvdService;

    @Inject
    HomeController(DvdService dvdService) {
        this.dvdService = dvdService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayDvdLibrary(Model model) {
        List<Dvd> dvdList = dvdService.list();

        model.addAttribute("dvdList", dvdList);

        return "library";
    }

}
