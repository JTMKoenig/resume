/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controllers;

import com.sg.vendingmachinespringmvc.model.Coins;
import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.service.InsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.OutOfStockException;
import com.sg.vendingmachinespringmvc.service.VendingMachineService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author jono
 */
@Controller
public class VendingMachineController {

    VendingMachineService service;

    @Inject
    public VendingMachineController(VendingMachineService service) {
        this.service = service;
    }

    @RequestMapping(value = "pickItem/{id}", method = RequestMethod.GET)
    public String getItem(@PathVariable("id") int id, Model model) {

        List<Item> items = service.getAllItems();
        String money = service.addMoney(5).toString();
        Item item = service.getItemById(id);
        int currentItem = item.getId();

        model.addAttribute("item", currentItem);
        model.addAttribute("money", money);
        model.addAttribute("items", items);

        return "vendMachine";
    }

    @RequestMapping(value = "addMoney/{type}", method = RequestMethod.GET)
    public String addMoney(@PathVariable("type") int type, Model model) {

        String money = service.addMoney(type).toString();
        List<Item> items = service.getAllItems();
        int currentItem = service.getCurrentItem();
        if (currentItem != 0) {
            model.addAttribute("item", currentItem);
        }

        model.addAttribute("money", money);
        model.addAttribute("items", items);

        return "vendMachine";
    }

    @RequestMapping(value = "buyItem//", method = RequestMethod.GET)
    public String buyItem(Model model) {

        List<Item> items = service.getAllItems();
        String message = "SELECT AN ITEM";

        model.addAttribute("message", message);
        model.addAttribute("items", items);

        return "vendMachine";

    }

    @RequestMapping(value = "buyItem//{money:.+}", method = RequestMethod.GET)
    public String buyItem(@PathVariable("money") String money, Model model) {

        List<Item> items = service.getAllItems();
        String message = "SELECT AN ITEM";

        model.addAttribute("message", message);
        model.addAttribute("money", money);
        model.addAttribute("items", items);

        return "vendMachine";

    }

    @RequestMapping(value = "buyItem/{id}/{money:.+}", method = RequestMethod.GET)
    public String buyItem(@PathVariable("id") int id, @PathVariable("money") String money, Model model) {
        Coins change = new Coins();
        String changeString = "";
        Item item = service.getItemById(id);
        BigDecimal amount = new BigDecimal(money).setScale(2, RoundingMode.HALF_UP);

        List<Item> items = service.getAllItems();

        try {
            change = service.buyItem(item, amount);
        } catch (InsufficientFundsException | OutOfStockException e) {
            String errorMessage = e.getMessage();
            int currentItem = service.getCurrentItem();
            if (currentItem != 0) {
                model.addAttribute("item", currentItem);
            }
            model.addAttribute("message", errorMessage);
            model.addAttribute("money", money);
            model.addAttribute("items", items);
            return "vendMachine";
        }

        changeString = "Quarters: " + change.getQuarters() + " Dimes: " + change.getDimes() + " Nickels: " + change.getNickels();

        items = service.getAllItems();
        String newMoney = "0.00";
        String message = "THANK YOU";

        model.addAttribute("money", newMoney);
        model.addAttribute("message", message);
        model.addAttribute("change", changeString);
        model.addAttribute("items", items);

        return "vendMachine";
    }

    @RequestMapping(value = "returnChange", method = RequestMethod.GET)
    public String returnChange(Model model) {

        int[] change = service.returnChange();
        String changeString = "Quarters: " + change[0] + " Dimes: " + change[1] + " Nickels: " + change[2];
        List<Item> items = service.getAllItems();
        String newMoney = "0.00";

        model.addAttribute("money", newMoney);
        model.addAttribute("change", changeString);
        model.addAttribute("items", items);

        service.resetCoins();

        return "vendMachine";
    }

}
