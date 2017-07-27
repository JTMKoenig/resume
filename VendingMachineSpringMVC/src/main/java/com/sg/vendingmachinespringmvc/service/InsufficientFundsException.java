/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

/**
 *
 * @author jono
 */
public class InsufficientFundsException extends Exception {

    InsufficientFundsException(String message) {
        super(message);
    }

}
