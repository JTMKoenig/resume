/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dao;

import com.sg.fidgetblog.dto.User;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Jack
 */
public class UserDaoImplTest {

    UserDao userDao;

    User user = new User();

    public UserDaoImplTest() {
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

        userDao = ctx.getBean("userDao", UserDao.class);
        
        ArrayList<String> userAuthorities = new ArrayList<>();
        
        userAuthorities.add("ROLE_ADMIN");
        userAuthorities.add("ROLE_USER");
        userAuthorities.add("ROLE_CONTENTMANAGER");

        user = new User();
        user.setUserName("ThatGuy");
        user.setPassWord("1234");
        user.setIsActive(true);
        user.setIsBanned(false);
        user.setAuthorities(userAuthorities);
    }

    @After
    public void tearDown() {
        ArrayList<User> users = new ArrayList<>(userDao.actuallyReadAllUsers());
        for(User user : users){
            userDao.deleteAllAuthoritiesByUserName(user.getUserName());
        }
    }

    /**
     * Test of createUser method, of class UserDaoImpl.
     */
    @Test
    public void testCreateUser() {
        userDao.createUser(user);
        User fromDao = userDao.readUserById(user.getUserId());
        assertTrue(user.equals(fromDao));
    }

    /**
     * Test of updateUser method, of class UserDaoImpl.
     */
    @Test
    public void testUpdateUser() {
        userDao.createUser(user);
        user.setUserName("NewName");
        userDao.updateUser(user);
        User fromDao = userDao.readUserById(user.getUserId());
        assertTrue(user.equals(fromDao));
    }

    /**
     * Test of deleteUserByUserName method, of class UserDaoImpl.
     */
    @Test
    public void testDeleteUserByUserName() {
        userDao.createUser(user);
        user.setIsActive(false);
        userDao.deleteUserByUserName(user.getUserName());
        assertTrue(user.equals(userDao.readUserById(user.getUserId())));
    }

    /**
     * Test of readAllUsers method, of class UserDaoImpl.
     */
    @Test
    public void testReadAllUsers() {
        userDao.createUser(user);
        User fromDao = userDao.readUserById(user.getUserId());
        ArrayList<User> users = new ArrayList<>(userDao.actuallyReadAllUsers());
        assertTrue(users.contains(fromDao));
    }

}
