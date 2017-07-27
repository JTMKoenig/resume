/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.service;

import com.sg.metabeingfinder.dto.Power;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jono
 */
public class PowerServiceTest {

    private PowerService service;

    public PowerServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        service = ctx.getBean("powerService", PowerService.class);

        //remove all powers
        List<Power> powers = service.getAllPowers();
        powers.forEach((power) -> {
            service.deletePower(power.getPowerId());
        });
    }

    public void comparePowers(Power fromDb, Power p) {
        assertEquals(fromDb.getPowerId(), p.getPowerId());
        assertEquals(fromDb.getPowerType(), p.getPowerType());
        assertEquals(fromDb.getPowerDescription(), p.getPowerDescription());
    }

    @After
    public void tearDown() {
        //remove all powers
        List<Power> powers = service.getAllPowers();
        powers.forEach((power) -> {
            service.deletePower(power.getPowerId());
        });
    }

    @Test
    public void addGetDeletePower() {
        Power p = new Power();
        p.setPowerType("Super");
        p.setPowerDescription("power");

        //ADD METHOD
        service.addPower(p);

        //GET METHOD
        Power fromDb = service.getOnePower(p.getPowerId());

        comparePowers(fromDb, p);

        //DELETE METHOD
        service.deletePower(p.getPowerId());
        assertNull(service.getOnePower(p.getPowerId()));
    }

    @Test
    public void addUpdatePower() {
        Power p = new Power();
        p.setPowerType("Super");
        p.setPowerDescription("power");

        //ADD METHOD
        service.addPower(p);

        p.setPowerDescription("duper power");
        service.updatePower(p);

        Power fromDb = service.getOnePower(p.getPowerId());

        comparePowers(fromDb, p);
    }

    @Test
    public void getAllPowers() {
        Power p = new Power();
        p.setPowerType("Super");
        p.setPowerDescription("power");

        //ADD METHOD
        service.addPower(p);

        Power p2 = new Power();
        p2.setPowerType("Lame");
        p2.setPowerDescription("sad");

        //ADD METHOD
        service.addPower(p2);

        List<Power> powers = service.getAllPowers();
        assertEquals(2, powers.size());
    }

}
