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
public class OrganizationMetabeingServiceTest {

    private OrganizationMetabeingService service;
    private OrganizationService oService;
    private MetabeingService mService;
    private LocationService lService;

    public OrganizationMetabeingServiceTest() {
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
        service = ctx.getBean("orgMetaService", OrganizationMetabeingService.class);
        oService = ctx.getBean("organizationService", OrganizationService.class);
        mService = ctx.getBean("metabeingService", MetabeingService.class);
        lService = ctx.getBean("locationService", LocationService.class);

        //remove all OrgMetas
        List<OrganizationMetabeing> orgMetas = service.getAllOrgMetas();
        orgMetas.forEach((orgMeta) -> {
            service.deleteOrgMeta(orgMeta.getOrganizationMetabeingId());
        });

    }

    @After
    public void tearDown() {
        //remove all OrgMetas
        List<OrganizationMetabeing> orgMetas = service.getAllOrgMetas();
        orgMetas.forEach((orgMeta) -> {
            service.deleteOrgMeta(orgMeta.getOrganizationMetabeingId());
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

        //remove all Metabeings
        List<Metabeing> metabeings = mService.getAllMetabeings();
        metabeings.forEach((metabeing) -> {
            mService.deleteMetabeing(metabeing.getMetabeingId());
        });

    }

    public void compareOrgMetas(OrganizationMetabeing fromDb, OrganizationMetabeing om) {
        assertEquals(fromDb.getOrganizationMetabeingId(), om.getOrganizationMetabeingId());
        assertEquals(fromDb.getOrganization().getOrganizationId(), om.getOrganization().getOrganizationId());
        assertEquals(fromDb.getMetabeing().getMetabeingId(), om.getMetabeing().getMetabeingId());
    }

    @Test
    public void addGetDeleteOrgMeta() {
        //create/add organization
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

        //create/add Metabeing
        Metabeing m = new Metabeing();
        m.setName("Superlady");
        m.setAlias("Tina Fey");
        m.setDescription("middle aged female, brown hair, wears loose pants");

        mService.addMetabeing(m);

        //create/add OrgMeta
        OrganizationMetabeing om = new OrganizationMetabeing();
        om.setOrganization(o);
        om.setMetabeing(m);
        service.addOrgMeta(om);

        //GET METHOD
        OrganizationMetabeing fromDb = service.getOneOrgMeta(om.getOrganizationMetabeingId());

        compareOrgMetas(fromDb, om);

        //DELETE METHOD
        service.deleteOrgMeta(om.getOrganizationMetabeingId());
        assertNull(service.getOneOrgMeta(om.getOrganizationMetabeingId()));

    }

    @Test
    public void addUpdateOrgMeta() {

        //create/add organization
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

        //create/add Metabeing
        Metabeing m = new Metabeing();
        m.setName("Superlady");
        m.setAlias("Tina Fey");
        m.setDescription("middle aged female, brown hair, wears loose pants");

        mService.addMetabeing(m);

        //create/add OrgMeta
        OrganizationMetabeing om = new OrganizationMetabeing();
        om.setOrganization(o);
        om.setMetabeing(m);
        service.addOrgMeta(om);

        //create/add Metabeing2
        Metabeing m2 = new Metabeing();
        m2.setName("Superbro");
        m2.setAlias("Tony");
        m2.setDescription("middle aged male, brown hair, wears pants");
        mService.addMetabeing(m2);
        om.setMetabeing(m2);
        service.updateOrgMeta(om);

        OrganizationMetabeing fromDb = service.getOneOrgMeta(om.getOrganizationMetabeingId());

        compareOrgMetas(fromDb, om);

    }

    @Test
    public void getAllOrgMetas() {
        //create/add organization
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

        //create/add Metabeing
        Metabeing m = new Metabeing();
        m.setName("Superlady");
        m.setAlias("Tina Fey");
        m.setDescription("middle aged female, brown hair, wears loose pants");

        mService.addMetabeing(m);

        //create/add OrgMeta
        OrganizationMetabeing om = new OrganizationMetabeing();
        om.setOrganization(o);
        om.setMetabeing(m);
        service.addOrgMeta(om);

        //create/add Metabeing2
        Metabeing m2 = new Metabeing();
        m2.setName("Superbro");
        m2.setAlias("Tony");
        m2.setDescription("middle aged male, brown hair, wears pants");
        mService.addMetabeing(m2);

        //create/add OrgMeta
        OrganizationMetabeing om2 = new OrganizationMetabeing();
        om2.setOrganization(o);
        om2.setMetabeing(m2);
        service.addOrgMeta(om2);

        List<OrganizationMetabeing> orgMetas = service.getAllOrgMetas();
        assertEquals(2, orgMetas.size());
    }

}
