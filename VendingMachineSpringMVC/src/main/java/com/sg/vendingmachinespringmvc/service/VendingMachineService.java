/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.model.Coins;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jono
 */
public class VendingMachineService {

    VendingMachineDao dao;

    @Inject
    VendingMachineService(VendingMachineDao dao) {
        this.dao = dao;
    }

    public BigDecimal addMoney(int type) {
        return dao.addMoney(type);
    }

    public Item getItemById(int id) {
        return dao.getItemByid(id);
    }

    public Coins buyItem(Item item, BigDecimal amount) throws OutOfStockException, InsufficientFundsException {
        Coins change = new Coins();
        if (item.getQty() < 1) {
            throw new OutOfStockException("OUT OF STOCK");
        }
        if (amount.compareTo(item.getPrice()) < 0) {
            BigDecimal shortAmount = item.getPrice().subtract(amount);
            throw new InsufficientFundsException("DEPOSIT: " + shortAmount);
        }
        if (item.getQty() > 0 && amount.compareTo(item.getPrice()) >= 0) {
            change = dao.buyItem(item);
        }
        return change;

    }

    public int[] returnChange() {
        return dao.returnChange();
    }

    public List<Item> getAllItems() {
        return dao.getAllItems();
    }

    public void loadMachine() {
        dao.loadMachine();
    }

    public int getCurrentItem() {
        return dao.getCurrentItem();
    }

    public void resetCoins() {
        dao.resetCoins();
    }

}
