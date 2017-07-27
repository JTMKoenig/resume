/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.contactlistspringmvc.dao;

import com.sg.contactlistspringmvc.model.Contact;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class ContactListDaoTest {
    
    private ContactListDao dao;
    
    public ContactListDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //ask Spring for DAO
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("contactListDao", ContactListDao.class);

        //remove all contacts
        List<Contact> contacts = dao.getAllContacts();
        for (Contact currentContact : contacts) {
            dao.removeContact(currentContact.getContactId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addContact, deleteContact, getContactById methods, of class
     * ContactListDao.
     */
    @Test
    public void addDeleteGetContact() {
        
        Contact nc = new Contact();
        nc.setFirstName("Mister");
        nc.setLastName("Rogers");
        nc.setCompany("PBS");
        nc.setEmail("friendly@neighbor.com");
        nc.setPhone("12345678");
        dao.addContact(nc);
        
        Contact fromDb = dao.getContactById(nc.getContactId());
        
        assertEquals(fromDb.getContactId(), nc.getContactId());
        assertEquals(fromDb.getFirstName(), nc.getFirstName());
        assertEquals(fromDb.getLastName(), nc.getLastName());
        assertEquals(fromDb.getCompany(), nc.getCompany());
        assertEquals(fromDb.getPhone(), nc.getPhone());
        assertEquals(fromDb.getEmail(), nc.getEmail());
        
        dao.removeContact(nc.getContactId());
        
        assertNull(dao.getContactById(nc.getContactId()));
        
    }

    /**
     * Test of updateContact method, of class ContactListDao.
     */
    @Test
    public void addUpdateContact() {
        
        Contact nc = new Contact();
        nc.setFirstName("Mister");
        nc.setLastName("T");
        nc.setCompany("A-Team");
        nc.setEmail("pity@thefool.com");
        nc.setPhone("15555555");
        dao.addContact(nc);
        
        nc.setPhone("16666666");
        dao.updateContact(nc);
        
        Contact fromDb = dao.getContactById(nc.getContactId());
        
        assertEquals(fromDb.getContactId(), nc.getContactId());
        assertEquals(fromDb.getFirstName(), nc.getFirstName());
        assertEquals(fromDb.getLastName(), nc.getLastName());
        assertEquals(fromDb.getCompany(), nc.getCompany());
        assertEquals(fromDb.getPhone(), nc.getPhone());
        assertEquals(fromDb.getEmail(), nc.getEmail());
    }

    /**
     * Test of getAllContacts method, of class ContactListDao.
     */
    @Test
    public void getAllContacts() {
        Contact nc = new Contact();
        nc.setFirstName("Mister");
        nc.setLastName("T");
        nc.setCompany("A-Team");
        nc.setEmail("pity@thefool.com");
        nc.setPhone("15555555");
        dao.addContact(nc);
        
        Contact nc2 = new Contact();
        nc2.setFirstName("Mister");
        nc2.setLastName("Rogers");
        nc2.setCompany("PBS");
        nc2.setEmail("friendly@neighbor.com");
        nc2.setPhone("12345678");
        dao.addContact(nc2);
        
        List<Contact> cList = dao.getAllContacts();
        assertEquals(cList.size(), 2);
        
    }

    /**
     * Test of searchContacts method, of class ContactListDao.
     */
    @Test
    public void searchContacts() {
        
        Contact nc = new Contact();
        nc.setFirstName("Mister");
        nc.setLastName("T");
        nc.setCompany("A-Team");
        nc.setEmail("pity@thefool.com");
        nc.setPhone("15555555");
        dao.addContact(nc);
        
        Contact nc2 = new Contact();
        nc2.setFirstName("Mister");
        nc2.setLastName("Rogers");
        nc2.setCompany("PBS");
        nc2.setEmail("friendly@neighbor.com");
        nc2.setPhone("12345678");
        dao.addContact(nc2);

        //Same LAST NAME as nc but different COMPANY
        Contact nc3 = new Contact();
        nc3.setFirstName("Donald");
        nc3.setLastName("Rogers");
        nc3.setCompany("NBC");
        nc3.setEmail("funny@sometimes.com");
        nc3.setPhone("18967564");
        dao.addContact(nc3);

        //SEARCH CRITERIA - find nc
        Map<SearchTerm, String> criteria = new HashMap();
        criteria.put(SearchTerm.LAST_NAME, "T");
        List<Contact> cList = dao.searchContacts(criteria);
        
        assertEquals(1, cList.size());
        
        assertEquals(nc.getContactId(), cList.get(0).getContactId());
        assertEquals(nc.getFirstName(), cList.get(0).getFirstName());
        assertEquals(nc.getLastName(), cList.get(0).getLastName());
        assertEquals(nc.getCompany(), cList.get(0).getCompany());
        assertEquals(nc.getPhone(), cList.get(0).getPhone());
        assertEquals(nc.getEmail(), cList.get(0).getEmail());

        //NEW SEARCH CRITERIA - find nc2 and nc3
        criteria.put(SearchTerm.LAST_NAME, "Rogers");
        cList = dao.searchContacts(criteria);
        assertEquals(2, cList.size());

        //ADD COMPANY to Search Criteria - find nc2
        criteria.put(SearchTerm.COMPANY, "PBS");
        cList = dao.searchContacts(criteria);
        
        assertEquals(1, cList.size());
        
        assertEquals(nc2.getContactId(), cList.get(0).getContactId());
        assertEquals(nc2.getFirstName(), cList.get(0).getFirstName());
        assertEquals(nc2.getLastName(), cList.get(0).getLastName());
        assertEquals(nc2.getCompany(), cList.get(0).getCompany());
        assertEquals(nc2.getPhone(), cList.get(0).getPhone());
        assertEquals(nc2.getEmail(), cList.get(0).getEmail());

        //CHANGE COMPANY to  NBC - find nc3
        criteria.put(SearchTerm.COMPANY, "NBC");
        cList = dao.searchContacts(criteria);
        
        assertEquals(1, cList.size());
        
        assertEquals(nc3.getContactId(), cList.get(0).getContactId());
        assertEquals(nc3.getFirstName(), cList.get(0).getFirstName());
        assertEquals(nc3.getLastName(), cList.get(0).getLastName());
        assertEquals(nc3.getCompany(), cList.get(0).getCompany());
        assertEquals(nc3.getPhone(), cList.get(0).getPhone());
        assertEquals(nc3.getEmail(), cList.get(0).getEmail());

        //CHANGE COMPANY to  Apple - find nothing
        criteria.put(SearchTerm.COMPANY, "Apple");
        cList = dao.searchContacts(criteria);
        
        assertEquals(0, cList.size());
        
    }
    
}
