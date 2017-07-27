/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.service;

import com.sg.metabeingfinder.dto.Location;
import com.sg.metabeingfinder.dto.Metabeing;
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
public class LocationServiceTest {

    private LocationService service;
    private SightingService sService;
    private SightingMetabeingService smService;
    private MetabeingService mService;

    public LocationServiceTest() {
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
        service = ctx.getBean("locationService", LocationService.class);
        sService = ctx.getBean("sightingService", SightingService.class);
        smService = ctx.getBean("sightingMetaService", SightingMetabeingService.class);
        mService = ctx.getBean("metabeingService", MetabeingService.class);

        //remove all Locations
        List<Location> locations = service.getAllLocations();
        locations.forEach((location) -> {
            service.deleteLocation(location.getLocationId());
        });
    }

    @After
    public void tearDown() {
        //remove all SightingMetas
        List<SightingMetabeing> sightingMetas = smService.getAllSightingMetas();
        sightingMetas.forEach((sightingMeta) -> {
            smService.deleteSightingMeta(sightingMeta.getSightingMetabeingId());
        });
        //remove all sightings
        List<Sighting> sightings = sService.getAllSightings();
        sightings.forEach((sighting) -> {
            sService.deleteSighting(sighting.getSightingId());
        });
        //remove all Metabeings
        List<Metabeing> metabeings = mService.getAllMetabeings();
        metabeings.forEach((metabeing) -> {
            mService.deleteMetabeing(metabeing.getMetabeingId());
        });
        //remove all Locations
        List<Location> locations = service.getAllLocations();
        locations.forEach((location) -> {
            service.deleteLocation(location.getLocationId());
        });

    }

    public void compareLocations(Location fromDb, Location l) {
        assertEquals(fromDb.getLocationId(), l.getLocationId());
        assertEquals(fromDb.getName(), l.getName());
        assertEquals(fromDb.getDescription(), l.getDescription());
        assertEquals(fromDb.getCountry(), l.getCountry());
        assertEquals(fromDb.getCity(), l.getCity());
        assertEquals(fromDb.getState(), l.getState());
        assertEquals(fromDb.getStreetAddress(), l.getStreetAddress());
        assertEquals(fromDb.getLatitude(), l.getLatitude());
        assertEquals(fromDb.getLongitude(), l.getLongitude());
    }

    @Test
    public void addGetDeleteLocation() {

        Location l = new Location();
        l.setName("Central Park");
        l.setDescription("Big ol park");
        l.setCountry("USA");
        l.setCity("New York City");
        l.setState("NY");
        l.setLatitude("40.7829 N");
        l.setLongitude("73.9654 W");

        //ADD METHOD
        service.addLocation(l);

        //GET METHOD
        Location fromDb = service.getOneLocation(l.getLocationId());

        compareLocations(fromDb, l);

        //DELETE METHOD
        service.deleteLocation(l.getLocationId());
        assertNull(service.getOneLocation(l.getLocationId()));

    }

    @Test
    public void addUpdateLocation() {
        Location l = new Location();
        l.setName("Central Park");
        l.setDescription("Big ol park");
        l.setCountry("USA");
        l.setCity("New York City");
        l.setState("NY");
        l.setLatitude("40.7829 N");
        l.setLongitude("73.9654 W");

        //ADD METHOD
        service.addLocation(l);

        l.setDescription("Huge PARK");
        service.updateLocation(l);

        Location fromDb = service.getOneLocation(l.getLocationId());

        compareLocations(fromDb, l);
    }

    @Test
    public void getAllLocations() {
        Location l = new Location();
        l.setName("Central Park");
        l.setDescription("Big ol park");
        l.setCountry("USA");
        l.setCity("New York City");
        l.setState("NY");
        l.setLatitude("40.7829 N");
        l.setLongitude("73.9654 W");

        service.addLocation(l);

        Location l2 = new Location();
        l2.setName("Taco Bell");
        l2.setDescription("mexican fast food restaurant");
        l2.setCountry("USA");
        l2.setCity("North Royalton");
        l2.setState("OH");
        l2.setStreetAddress("6447 North Royalton Road");
        l2.setLatitude("41.3173 N");
        l2.setLongitude("81.7246 W");

        service.addLocation(l2);

        List<Location> locations = service.getAllLocations();
        assertEquals(locations.size(), 2);
    }

    @Test
    public void selectLocationByMeta() {
        Location l = new Location();
        l.setName("Central Park");
        l.setDescription("Big ol park");
        l.setCountry("USA");
        l.setCity("New York City");
        l.setState("NY");
        l.setLatitude("40.7829 N");
        l.setLongitude("73.9654 W");

        service.addLocation(l);

        Sighting s = new Sighting();
        s.setDescription("First Sighting");
        LocalDate date = LocalDate.of(2017, Month.MARCH, 20);
        s.setDate(date);
        s.setLocation(l);
        sService.addSighting(s);

        Metabeing m = new Metabeing();
        m.setName("Superlady");
        m.setAlias("Tina Fey");
        m.setDescription("middle aged female, brown hair, wears loose pants");
        mService.addMetabeing(m);

        //create/add SightingMeta
        SightingMetabeing sm = new SightingMetabeing();
        sm.setSighting(s);
        sm.setMetabeing(m);
        smService.addSightingMetabeing(sm);

        List<Location> locations = service.getLocationByMetabeing(m.getMetabeingId());
        assertEquals(1, locations.size());

    }

}
