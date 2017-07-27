/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.service;

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
public class UserServiceImplTest {

    UserService userService;

    User user = new User();

    public UserServiceImplTest() {
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

        userService = ctx.getBean("userService", UserService.class);
        
        ArrayList<String> authorities = new ArrayList<>();
        authorities.add("ROLE_ADMIN");
        authorities.add("ROLE_USER");
        authorities.add("ROLE_CONTENTMANAGER");

        user = new User();
        user.setUserName("ThatGuy");
        user.setPassWord("1234");
        user.setIsActive(true);
        user.setIsBanned(false);
        user.setAuthorities(authorities);
    }

    @After
    public void tearDown() {
        ArrayList<User> users = new ArrayList<>(userService.actuallyReadAllUsers());
        for(User user : users){
            userService.deleteAllAuthoritiesByUserName(user.getUserName());
            userService.deleteUser(user.getUserName());
        }
    }

    /**
     * Test of createUser method, of class UserServiceImpl.
     */
    @Test
    public void testCreateUser() {
        userService.createUser(user);
        User fromDao = userService.readUserById(user.getUserId());
        assertTrue(user.equals(fromDao));
    }

    /**
     * Test of updateUser method, of class UserServiceImpl.
     */
    @Test
    public void testUpdateUser() {
        userService.createUser(user);
        user.setUserName("NewName");
        userService.updateUser(user);
        assertTrue(user.equals(userService.readUserById(user.getUserId())));
    }

    /**
     * Test of deleteUserById method, of class UserServiceImpl.
     */
    @Test
    public void testDeleteUser() {
        userService.createUser(user);
        user.setIsActive(false);
        userService.deleteUser(user.getUserName());
        assertTrue(user.equals(userService.readUserById(user.getUserId())));
    }

    /**
     * Test of readAllUsers method, of class UserServiceImpl.
     */
    @Test
    public void testReadAllUsers() {
        userService.createUser(user);
        assertTrue(userService.actuallyReadAllUsers().contains(userService.readUserById(user.getUserId())));
    }

}
