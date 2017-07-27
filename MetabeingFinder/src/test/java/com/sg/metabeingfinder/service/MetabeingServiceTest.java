/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.service;

import com.sg.metabeingfinder.dto.Location;
import com.sg.metabeingfinder.dto.Metabeing;
import com.sg.metabeingfinder.dto.Organization;
import com.sg.metabeingfinder.dto.OrganizationMetabeing;
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
public class MetabeingServiceTest {

    private MetabeingService service;
    private LocationService lService;
    private SightingService sService;
    private SightingMetabeingService smService;
    private OrganizationService oService;
    private OrganizationMetabeingService omService;

    public MetabeingServiceTest() {
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
        service = ctx.getBean("metabeingService", MetabeingService.class);
        lService = ctx.getBean("locationService", LocationService.class);
        sService = ctx.getBean("sightingService", SightingService.class);
        smService = ctx.getBean("sightingMetaService", SightingMetabeingService.class);
        oService = ctx.getBean("organizationService", OrganizationService.class);
        omService = ctx.getBean("orgMetaService", OrganizationMetabeingService.class);

        //remove all Metabeings
        List<Metabeing> metabeings = service.getAllMetabeings();
        metabeings.forEach((metabeing) -> {
            service.deleteMetabeing(metabeing.getMetabeingId());
        });

    }

    @After
    public void tearDown() {
        //remove all SightingMetas
        List<SightingMetabeing> sightingMetas = smService.getAllSightingMetas();
        sightingMetas.forEach((sightingMeta) -> {
            smService.deleteSightingMeta(sightingMeta.getSightingMetabeingId());
        });
        //remove all OrgMetas
        List<OrganizationMetabeing> orgMetas = omService.getAllOrgMetas();
        orgMetas.forEach((orgMeta) -> {
            omService.deleteOrgMeta(orgMeta.getOrganizationMetabeingId());
        });
        //remove all Metabeings
        List<Metabeing> metabeings = service.getAllMetabeings();
        metabeings.forEach((metabeing) -> {
            service.deleteMetabeing(metabeing.getMetabeingId());
        });
        //remove all sightings
        List<Sighting> sightings = sService.getAllSightings();
        sightings.forEach((sighting) -> {
            sService.deleteSighting(sighting.getSightingId());
        });
        //remove all Organizations
        List<Organization> organizations = oService.getAllOrganizations();
        organizations.forEach((organization) -> {
            oService.deleteOrganization(organization.getOrganizationId());
        });

        //remove all Locations
        List<Location> locations = lService.getAllLocations();
        locations.forEach((location) -> {
            lService.deleteLocation(location.getLocationId());
        });

    }

    public void compareMetabeings(Metabeing fromDb, Metabeing m) {
        assertEquals(fromDb.getMetabeingId(), m.getMetabeingId());
        assertEquals(fromDb.getName(), m.getName());
        assertEquals(fromDb.getAlias(), m.getAlias());
        assertEquals(fromDb.getDescription(), m.getDescription());
    }

    @Test
    public void addGetDeleteMetabeing() {

        Metabeing m = new Metabeing();
        m.setName("Superlady");
        m.setAlias("Tina Fey");
        m.setDescription("middle aged female, brown hair, wears loose pants");

        //ADD METHOD
        service.addMetabeing(m);

        //GET METHOD
        Metabeing fromDb = service.getOneMetabeing(m.getMetabeingId());

        compareMetabeings(fromDb, m);

        //DELETE METHOD
        service.deleteMetabeing(m.getMetabeingId());
        assertNull(service.getOneMetabeing(m.getMetabeingId()));
    }

    @Test
    public void addUpdateMetabeing() {
        Metabeing m = new Metabeing();
        m.setName("Superlady");
        m.setAlias("Tina Fey");
        m.setDescription("middle aged female, brown hair, wears loose pants");

        //ADD METHOD
        service.addMetabeing(m);

        m.setDescription("middle aged female, brown hair, wears loose pants and tight shirts");
        service.updateMetabeing(m);

        Metabeing fromDb = service.getOneMetabeing(m.getMetabeingId());

        compareMetabeings(fromDb, m);
    }

    @Test
    public void getAllMetabeings() {
        Metabeing m = new Metabeing();
        m.setName("Superlady");
        m.setAlias("Tina Fey");
        m.setDescription("middle aged female, brown hair, wears loose pants");

        //ADD METHOD
        service.addMetabeing(m);

        Metabeing m2 = new Metabeing();
        m2.setName("Superbro");
        m2.setAlias("Rocky");
        m2.setDescription("middle aged male, brown hair, wears no pants");

        //ADD METHOD
        service.addMetabeing(m2);

        List<Metabeing> metabeings = service.getAllMetabeings();
        assertEquals(metabeings.size(), 2);

    }

    @Test
    public void getMetaByLocation() {
        //add Meta
        Metabeing m = new Metabeing();
        m.setName("Superlady");
        m.setAlias("Tina Fey");
        m.setDescription("middle aged female, brown hair, wears loose pants");
        service.addMetabeing(m);

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
        sService.addSighting(s);

        //create/add SightingMeta
        SightingMetabeing sm = new SightingMetabeing();
        sm.setSighting(s);
        sm.setMetabeing(m);
        smService.addSightingMetabeing(sm);

        List<Metabeing> metabeings = service.getMetaByLocation(l.getLocationId());

        assertEquals(1, metabeings.size());
    }

    @Test
    public void getMetaByOrganization() {
        Organization o = new Organization();
        o.setName("Secret Org");
        o.setDescription("Super secret Org");

        Location l = new Location();
        l.setName("Central Park");
        l.setDescription("Big ol park");
        l.setCountry("USA");
        l.setCity("New York City");
        l.setState("NY");
        l.setLatitude("40.7829 N");
        l.setLongitude("73.9654 W");
        lService.addLocation(l);
        o.setLocation(l);
        oService.addOrganization(o);

        //add Meta
        Metabeing m = new Metabeing();
        m.setName("Superlady");
        m.setAlias("Tina Fey");
        m.setDescription("middle aged female, brown hair, wears loose pants");
        service.addMetabeing(m);

        //create/add OrgMeta
        OrganizationMetabeing om = new OrganizationMetabeing();
        om.setOrganization(o);
        om.setMetabeing(m);
        omService.addOrgMeta(om);

        List<Metabeing> metabeings = service.getMetaByOrganization(o.getOrganizationId());
        assertEquals(1, metabeings.size());

    }

}
