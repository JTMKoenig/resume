/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.service;

import com.sg.metabeingfinder.dto.Location;
import com.sg.metabeingfinder.dto.Metabeing;
import com.sg.metabeingfinder.dto.PowerMetabeing;
import com.sg.metabeingfinder.dto.Sighting;
import com.sg.metabeingfinder.dto.SightingMetabeing;
import java.time.LocalDate;
import java.time.Month;
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
public class SightingMetabeingServiceTest {

    private SightingMetabeingService service;
    private SightingService sService;
    private MetabeingService mService;
    private LocationService lService;

    public SightingMetabeingServiceTest() {
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
        service = ctx.getBean("sightingMetaService", SightingMetabeingService.class);
        sService = ctx.getBean("sightingService", SightingService.class);
        mService = ctx.getBean("metabeingService", MetabeingService.class);
        lService = ctx.getBean("locationService", LocationService.class);

        //remove all SightingMetas
        List<SightingMetabeing> sightingMetas = service.getAllSightingMetas();
        sightingMetas.forEach((sightingMeta) -> {
            service.deleteSightingMeta(sightingMeta.getSightingMetabeingId());
        });

    }

    @After
    public void tearDown() {
        //remove all SightingMetas
        List<SightingMetabeing> sightingMetas = service.getAllSightingMetas();
        sightingMetas.forEach((sightingMeta) -> {
            service.deleteSightingMeta(sightingMeta.getSightingMetabeingId());
        });
        //remove all sightings
        List<Sighting> sightings = sService.getAllSightings();
        sightings.forEach((sighting) -> {
            sService.deleteSighting(sighting.getSightingId());
        });
        //remove all Locations
        List<Location> locations = lService.getAllLocations();
        locations.forEach((location) -> {
            lService.deleteLocation(location.getLocationId());
        });
        //remove all Metabeings
        List<Metabeing> metabeings = mService.getAllMetabeings();
        metabeings.forEach((metabeing) -> {
            mService.deleteMetabeing(metabeing.getMetabeingId());
        });
    }

    public void compareSightingMetas(SightingMetabeing fromDb, SightingMetabeing sm) {
        assertEquals(fromDb.getSightingMetabeingId(), sm.getSightingMetabeingId());
        assertEquals(fromDb.getSighting().getSightingId(), sm.getSighting().getSightingId());
        assertEquals(fromDb.getMetabeing().getMetabeingId(), sm.getMetabeing().getMetabeingId());
    }

    @Test
    public void addGetDeleteSightingMetabeing() {
        //create/add sighting
        Sighting s = new Sighting();
        s.setDescription("First Sighting");
        LocalDate date = LocalDate.of(2017, Month.MARCH, 20);
        s.setDate(date);

        Location l = new Location();
        l.setName("Central Park");
        l.setDescription("Big ol park");
        l.setCountry("USA");
        l.setCity("New York City");
        l.setState("NY");
        l.setLatitude("40.7829 N");
        l.setLongitude("73.9654 W");
        lService.addLocation(l);
        s.setLocation(l);
        sService.addSighting(s);

        //create/add Metabeing
        Metabeing m = new Metabeing();
        m.setName("Superlady");
        m.setAlias("Tina Fey");
        m.setDescription("middle aged female, brown hair, wears loose pants");
        mService.addMetabeing(m);

        //create/add SightingMeta
        SightingMetabeing sm = new SightingMetabeing();
        sm.setSighting(s);
        sm.setMetabeing(m);
        service.addSightingMetabeing(sm);

        //Get SightingMetabeing from Db
        SightingMetabeing fromDb = service.getOneSightingMetabeing(sm.getSightingMetabeingId());

        compareSightingMetas(fromDb, sm);

        //Delete SightingMeta from Db
        service.deleteSightingMeta(sm.getSightingMetabeingId());
        assertNull(service.getOneSightingMetabeing(sm.getSightingMetabeingId()));
    }

    @Test
    public void addUpdateSightingMeta() {
        //create/add sighting
        Sighting s = new Sighting();
        s.setDescription("First Sighting");
        LocalDate date = LocalDate.of(2017, Month.MARCH, 20);
        s.setDate(date);

        Location l = new Location();
        l.setName("Central Park");
        l.setDescription("Big ol park");
        l.setCountry("USA");
        l.setCity("New York City");
        l.setState("NY");
        l.setLatitude("40.7829 N");
        l.setLongitude("73.9654 W");
        lService.addLocation(l);
        s.setLocation(l);
        sService.addSighting(s);

        //create/add Metabeing
        Metabeing m = new Metabeing();
        m.setName("Superlady");
        m.setAlias("Tina Fey");
        m.setDescription("middle aged female, brown hair, wears loose pants");
        mService.addMetabeing(m);

        //create/add SightingMeta
        SightingMetabeing sm = new SightingMetabeing();
        sm.setSighting(s);
        sm.setMetabeing(m);
        service.addSightingMetabeing(sm);

        //create/add Metabeing2
        Metabeing m2 = new Metabeing();
        m2.setName("Superbro");
        m2.setAlias("Tony");
        m2.setDescription("middle aged male, brown hair, wears pants");
        mService.addMetabeing(m2);
        sm.setMetabeing(m2);
        service.updateSightingMetabeing(sm);

        SightingMetabeing fromDb = service.getOneSightingMetabeing(sm.getSightingMetabeingId());
        compareSightingMetas(fromDb, sm);
    }

    @Test
    public void getAllSightingMetas() {
        //create/add sighting
        Sighting s = new Sighting();
        s.setDescription("First Sighting");
        LocalDate date = LocalDate.of(2017, Month.MARCH, 20);
        s.setDate(date);

        Location l = new Location();
        l.setName("Central Park");
        l.setDescription("Big ol park");
        l.setCountry("USA");
        l.setCity("New York City");
        l.setState("NY");
        l.setLatitude("40.7829 N");
        l.setLongitude("73.9654 W");
        lService.addLocation(l);
        s.setLocation(l);
        sService.addSighting(s);

        //create/add Metabeing
        Metabeing m = new Metabeing();
        m.setName("Superlady");
        m.setAlias("Tina Fey");
        m.setDescription("middle aged female, brown hair, wears loose pants");
        mService.addMetabeing(m);

        //create/add SightingMeta
        SightingMetabeing sm = new SightingMetabeing();
        sm.setSighting(s);
        sm.setMetabeing(m);
        service.addSightingMetabeing(sm);

        //create/add Metabeing2
        Metabeing m2 = new Metabeing();
        m2.setName("Superbro");
        m2.setAlias("Tony");
        m2.setDescription("middle aged male, brown hair, wears pants");
        mService.addMetabeing(m2);

        //create/add SightingMeta2
        SightingMetabeing sm2 = new SightingMetabeing();
        sm2.setSighting(s);
        sm2.setMetabeing(m2);
        service.addSightingMetabeing(sm2);

        List<SightingMetabeing> sightingMetas = service.getAllSightingMetas();
        assertEquals(2, sightingMetas.size());
    }

}
