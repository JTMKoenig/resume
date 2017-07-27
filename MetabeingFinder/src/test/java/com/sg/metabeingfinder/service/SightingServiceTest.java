/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.service;

import com.sg.metabeingfinder.dto.Location;
import com.sg.metabeingfinder.dto.Sighting;
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
public class SightingServiceTest {

    private SightingService service;
    private LocationService lService;

    public SightingServiceTest() {
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
        service = ctx.getBean("sightingService", SightingService.class);
        lService = ctx.getBean("locationService", LocationService.class);

        //remove all sightings
        List<Sighting> sightings = service.getAllSightings();
        sightings.forEach((sighting) -> {
            service.deleteSighting(sighting.getSightingId());
        });

    }

    @After
    public void tearDown() {
        //remove all sightings
        List<Sighting> sightings = service.getAllSightings();
        sightings.forEach((sighting) -> {
            service.deleteSighting(sighting.getSightingId());
        });
        //remove all Locations
        List<Location> locations = lService.getAllLocations();
        locations.forEach((location) -> {
            lService.deleteLocation(location.getLocationId());
        });
    }

    public void compareSightings(Sighting fromDb, Sighting s) {
        assertEquals(fromDb.getSightingId(), s.getSightingId());
        assertEquals(fromDb.getDescription(), s.getDescription());
        assertEquals(fromDb.getDate(), s.getDate());
        assertEquals(fromDb.getLocation().getLocationId(), s.getLocation().getLocationId());
    }

    @Test
    public void addGetDeleteSighting() {
        Sighting s = new Sighting();
        s.setDescription("First Sighting");
        LocalDate date = LocalDate.of(2017, Month.MARCH, 20);
        s.setDate(date);

        //add new location
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

        //ADD SIGHTING
        service.addSighting(s);

        //GET SIGHTING
        Sighting fromDb = service.getOneSighting(s.getSightingId());

        compareSightings(fromDb, s);

        //DELETE SIGHTING
        service.deleteSighting(s.getSightingId());
        assertNull(service.getOneSighting(s.getSightingId()));

    }

    @Test
    public void addUpdateSighting() {
        Sighting s = new Sighting();
        s.setDescription("First Sighting");
        LocalDate date = LocalDate.of(2017, Month.MARCH, 20);
        s.setDate(date);

        //add new location
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

        //ADD SIGHTING
        service.addSighting(s);

        s.setDescription("BEST SIGHTING EVER");
        service.updateSighting(s);

        Sighting fromDb = service.getOneSighting(s.getSightingId());

        assertEquals("BEST SIGHTING EVER", fromDb.getDescription());
        compareSightings(fromDb, s);
    }

    @Test
    public void getAllSightings() {
        Sighting s = new Sighting();
        s.setDescription("First Sighting");
        LocalDate date = LocalDate.of(2017, Month.MARCH, 20);
        s.setDate(date);

        //add new location
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

        //ADD SIGHTING
        service.addSighting(s);

        Sighting s2 = new Sighting();
        s2.setDescription("First Sighting");
        LocalDate date2 = LocalDate.of(2017, Month.MARCH, 18);
        s2.setDate(date);
        s2.setLocation(l);

        //ADD SIGHTING
        service.addSighting(s2);

        List<Sighting> sightings = service.getAllSightings();
        assertEquals(2, sightings.size());

    }

    @Test
    public void getSightingByDate() {
        Sighting s = new Sighting();
        s.setDescription("First Sighting");
        LocalDate date = LocalDate.of(2017, Month.MARCH, 20);
        s.setDate(date);

        //add new location
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

        //ADD SIGHTING
        service.addSighting(s);
    }

}
