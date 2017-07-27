/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Coins;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jono
 */
public class VendingMachineDaoImpl implements VendingMachineDao {

    private Map<Integer, Item> items = new HashMap();
    private int[] coins = {0, 0, 0};
    private BigDecimal money = new BigDecimal("0.00");
    private int currentItem;

    @Override
    public BigDecimal addMoney(int type) {
        if (type == 1) {
            coins[0] += 4;
            money = money.add(new BigDecimal("1.00"));
        }
        if (type == 2) {
            coins[0] += 1;
            money = money.add(new BigDecimal("0.25"));
        }
        if (type == 3) {
            coins[1] += 1;
            money = money.add(new BigDecimal("0.10"));
        }
        if (type == 4) {
            coins[2] += 1;
            money = money.add(new BigDecimal("0.05"));
        }

        return money;

    }

    @Override
    public Coins buyItem(Item item) {
        BigDecimal quarter = new BigDecimal("0.25");
        BigDecimal dime = new BigDecimal("0.10");
        BigDecimal nickel = new BigDecimal("0.05");

        BigDecimal change = money.subtract(item.getPrice());

        BigDecimal quarters = change.divide(quarter, 0, RoundingMode.DOWN);
        change = change.subtract(quarters.multiply(quarter));
        BigDecimal dimes = change.divide(dime, 0, RoundingMode.DOWN);
        change = change.subtract(dimes.multiply(dime));
        BigDecimal nickels = change.divide(nickel, 0, RoundingMode.DOWN);

        Coins changeCoins = new Coins();
        changeCoins.setDimes(dimes);
        changeCoins.setNickels(nickels);
        changeCoins.setQuarters(quarters);

        money = new BigDecimal("0.00");
        resetCoins();
        currentItem = 0;
        item.setQty(item.getQty() - 1);
        return changeCoins;

    }

    @Override
    public int[] returnChange() {
        money = new BigDecimal("0.00");
        return coins;
    }

    @Override
    public List<Item> getAllItems() {
        return new ArrayList(items.values());
    }

    @Override
    public void loadMachine() {
        Item item0 = new Item(1, "Beach Ball", new BigDecimal("3.50"), 5);
        Item item1 = new Item(2, "Large Towel", new BigDecimal("5.00"), 2);
        Item item2 = new Item(3, "Extra Sand", new BigDecimal("1.25"), 8);
        Item item3 = new Item(4, "Flip Flops", new BigDecimal("6.00"), 3);
        Item item4 = new Item(5, "Tiny Shovel", new BigDecimal("1.00"), 9);
        Item item5 = new Item(6, "Medium Shovel", new BigDecimal("2.00"), 5);
        Item item6 = new Item(7, "XL Shovel", new BigDecimal("5.50"), 1);
        Item item7 = new Item(8, "Sunscreen", new BigDecimal("4.00"), 8);
        Item item8 = new Item(9, "Water", new BigDecimal("3.50"), 2);

        items.put(item0.getId(), item0);
        items.put(item1.getId(), item1);
        items.put(item2.getId(), item2);
        items.put(item3.getId(), item3);
        items.put(item4.getId(), item4);
        items.put(item5.getId(), item5);
        items.put(item6.getId(), item6);
        items.put(item7.getId(), item7);
        items.put(item8.getId(), item8);
    }

    @Override
    public Item getItemByid(int id) {
        currentItem = id;
        return items.get(id);
    }

    @Override
    public int getCurrentItem() {
        return currentItem;
    }

    @Override
    public void resetCoins() {
        coins[0] = 0;
        coins[1] = 0;
        coins[2] = 0;
    }

}
