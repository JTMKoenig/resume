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
public class OrganizationServiceTest {

    private OrganizationService service;
    private LocationService lService;
    private OrganizationMetabeingService omService;
    private MetabeingService mService;

    public OrganizationServiceTest() {
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
        service = ctx.getBean("organizationService", OrganizationService.class);
        lService = ctx.getBean("locationService", LocationService.class);
        omService = ctx.getBean("orgMetaService", OrganizationMetabeingService.class);
        mService = ctx.getBean("metabeingService", MetabeingService.class);

        //remove all Organizations
        List<Organization> organizations = service.getAllOrganizations();
        organizations.forEach((organization) -> {
            service.deleteOrganization(organization.getOrganizationId());
        });
    }

    @After
    public void tearDown() {
        //remove all OrgMetas
        List<OrganizationMetabeing> orgMetas = omService.getAllOrgMetas();
        orgMetas.forEach((orgMeta) -> {
            omService.deleteOrgMeta(orgMeta.getOrganizationMetabeingId());
        });
        //remove all Metabeings
        List<Metabeing> metabeings = mService.getAllMetabeings();
        metabeings.forEach((metabeing) -> {
            mService.deleteMetabeing(metabeing.getMetabeingId());
        });
        //remove all Organizations
        List<Organization> organizations = service.getAllOrganizations();
        organizations.forEach((organization) -> {
            service.deleteOrganization(organization.getOrganizationId());
        });
        //remove all Locations
        List<Location> locations = lService.getAllLocations();
        locations.forEach((location) -> {
            lService.deleteLocation(location.getLocationId());
        });
    }

    public void compareOrganizations(Organization fromDb, Organization o) {
        assertEquals(fromDb.getOrganizationId(), o.getOrganizationId());
        assertEquals(fromDb.getName(), o.getName());
        assertEquals(fromDb.getDescription(), o.getDescription());
        assertEquals(fromDb.getLocation().getLocationId(), o.getLocation().getLocationId());
    }

    @Test
    public void addGetDeleteOrganization() {
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
        service.addOrganization(o);

        Organization fromDb = service.getOneOrganization(o.getOrganizationId());

        compareOrganizations(fromDb, o);

        service.deleteOrganization(o.getOrganizationId());
        assertNull(service.getOneOrganization(o.getOrganizationId()));
    }

    @Test
    public void addUpdateOrganization() {
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
        o.setLocation(l);
        service.addOrganization(o);

        o.setDescription("Someone found it");
        service.updateOrganization(o);

        Organization fromDb = service.getOneOrganization(o.getOrganizationId());

        compareOrganizations(fromDb, o);

    }

    @Test
    public void getAllOrganizations() {
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
        o.setLocation(l);
        service.addOrganization(o);

        Organization o2 = new Organization();
        o2.setName("Best Org");
        o2.setDescription("just alright");
        o2.setLocation(l);
        service.addOrganization(o2);

        List<Organization> organizations = service.getAllOrganizations();
        assertEquals(2, organizations.size());

    }

    @Test
    public void getOrganizationByMeta() {
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
        o.setLocation(l);
        service.addOrganization(o);

        Metabeing m = new Metabeing();
        m.setName("Superlady");
        m.setAlias("Tina Fey");
        m.setDescription("middle aged female, brown hair, wears loose pants");
        mService.addMetabeing(m);

        //create/add OrgMeta
        OrganizationMetabeing om = new OrganizationMetabeing();
        om.setOrganization(o);
        om.setMetabeing(m);
        omService.addOrgMeta(om);

        List<Organization> organizations = service.getOrganizationByMeta(m.getMetabeingId());
        assertEquals(1, organizations.size());
    }

}
