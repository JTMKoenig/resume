/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.service;

import com.sg.metabeingfinder.dto.Metabeing;
import com.sg.metabeingfinder.dto.Power;
import com.sg.metabeingfinder.dto.PowerMetabeing;
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
public class PowerMetabeingServiceTest {

    PowerMetabeingService service;
    PowerService pService;
    MetabeingService mService;

    public PowerMetabeingServiceTest() {
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
        service = ctx.getBean("powerMetaService", PowerMetabeingService.class);
        pService = ctx.getBean("powerService", PowerService.class);
        mService = ctx.getBean("metabeingService", MetabeingService.class);

        //remove all PowerMetas
        List<PowerMetabeing> powerMetas = service.getAllPowerMetas();
        powerMetas.forEach((powerMeta) -> {
            service.deletePowerMeta(powerMeta.getPowerMetabeingId());
        });
    }

    @After
    public void tearDown() {
        //remove all PowerMetas
        List<PowerMetabeing> powerMetas = service.getAllPowerMetas();
        powerMetas.forEach((powerMeta) -> {
            service.deletePowerMeta(powerMeta.getPowerMetabeingId());
        });
        //remove all powers
        List<Power> powers = pService.getAllPowers();
        powers.forEach((power) -> {
            pService.deletePower(power.getPowerId());
        });
        //remove all Metabeings
        List<Metabeing> metabeings = mService.getAllMetabeings();
        metabeings.forEach((metabeing) -> {
            mService.deleteMetabeing(metabeing.getMetabeingId());
        });
    }

    public void comparePowerMetas(PowerMetabeing fromDb, PowerMetabeing pm) {
        assertEquals(fromDb.getPowerMetabeingId(), pm.getPowerMetabeingId());
        assertEquals(fromDb.getPower().getPowerId(), pm.getPower().getPowerId());
        assertEquals(fromDb.getMetabeing().getMetabeingId(), pm.getMetabeing().getMetabeingId());
    }

    @Test
    public void addGetDeletePowerMeta() {
        //create/add Power
        Power p = new Power();
        p.setPowerType("Super");
        p.setPowerDescription("very cool");
        pService.addPower(p);

        //create/add Metabeing
        Metabeing m = new Metabeing();
        m.setName("Superlady");
        m.setAlias("Tina Fey");
        m.setDescription("middle aged female, brown hair, wears loose pants");
        mService.addMetabeing(m);

        //create/add PowerMeta
        PowerMetabeing pm = new PowerMetabeing();
        pm.setPower(p);
        pm.setMetabeing(m);
        service.addPowerMeta(pm);

        //Get PowerMeta
        PowerMetabeing fromDb = service.getOnePowerMeta(pm.getPowerMetabeingId());

        comparePowerMetas(fromDb, pm);

        //Delete PowerMeta
        service.deletePowerMeta(pm.getPowerMetabeingId());
        assertNull(service.getOnePowerMeta(pm.getPowerMetabeingId()));

    }

    @Test
    public void addUpdatePowerMeta() {
        //create/add Power
        Power p = new Power();
        p.setPowerType("Super");
        p.setPowerDescription("very cool");
        pService.addPower(p);

        //create/add Metabeing
        Metabeing m = new Metabeing();
        m.setName("Superlady");
        m.setAlias("Tina Fey");
        m.setDescription("middle aged female, brown hair, wears loose pants");
        mService.addMetabeing(m);

        //create/add PowerMeta
        PowerMetabeing pm = new PowerMetabeing();
        pm.setPower(p);
        pm.setMetabeing(m);
        service.addPowerMeta(pm);

        //create/add Metabeing2
        Metabeing m2 = new Metabeing();
        m2.setName("Superbro");
        m2.setAlias("Tony");
        m2.setDescription("middle aged male, brown hair, wears pants");
        mService.addMetabeing(m2);
        pm.setMetabeing(m2);

        service.updatePowerMetabeing(pm);

        PowerMetabeing fromDb = service.getOnePowerMeta(pm.getPowerMetabeingId());
        comparePowerMetas(fromDb, pm);
    }

    @Test
    public void getAllPowerMetas() {
        //create/add Power
        Power p = new Power();
        p.setPowerType("Super");
        p.setPowerDescription("very cool");
        pService.addPower(p);

        //create/add Metabeing
        Metabeing m = new Metabeing();
        m.setName("Superlady");
        m.setAlias("Tina Fey");
        m.setDescription("middle aged female, brown hair, wears loose pants");
        mService.addMetabeing(m);

        //create/add PowerMeta
        PowerMetabeing pm = new PowerMetabeing();
        pm.setPower(p);
        pm.setMetabeing(m);
        service.addPowerMeta(pm);

        //create/add Power2
        Power p2 = new Power();
        p2.setPowerType("Lame");
        p2.setPowerDescription("not cool");
        pService.addPower(p2);

        //create/add PowerMeta2
        PowerMetabeing pm2 = new PowerMetabeing();
        pm2.setPower(p2);
        pm2.setMetabeing(m);
        service.addPowerMeta(pm2);

        List<PowerMetabeing> powerMetas = service.getAllPowerMetas();
        assertEquals(2, powerMetas.size());

    }

}
