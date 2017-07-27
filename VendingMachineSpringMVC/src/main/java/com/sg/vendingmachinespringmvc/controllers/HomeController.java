package com.sg.vendingmachinespringmvc.controllers;

import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.service.VendingMachineService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    VendingMachineService service;

    @Inject
    public HomeController(VendingMachineService service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String sayHi(Model model) {

        return "welcome";
    }

    @RequestMapping(value = "loadMachine", method = RequestMethod.GET)
    public String stockMachine(Model model) {

        service.loadMachine();
        List<Item> items = service.getAllItems();

        model.addAttribute("items", items);

        return "vendMachine";
    }
}
