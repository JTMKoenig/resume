/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Coins;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jono
 */
public interface VendingMachineDao {

    public BigDecimal addMoney(int type);

    public Coins buyItem(Item item);

    public int[] returnChange();

    public List<Item> getAllItems();

    public void loadMachine();

    public Item getItemByid(int id);

    public int getCurrentItem();

    public void resetCoins();

}
