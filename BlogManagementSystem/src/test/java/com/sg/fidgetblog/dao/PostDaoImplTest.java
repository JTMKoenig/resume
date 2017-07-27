/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dao;

import com.sg.fidgetblog.dto.Post;
import com.sg.fidgetblog.dto.User;
import java.time.LocalDate;
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
public class PostDaoImplTest {

    PostDao postDao;
    UserDao userDao;

    User user = new User();
    Post post = new Post();

    public PostDaoImplTest() {
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

        postDao = ctx.getBean("postDao", PostDao.class);
        userDao = ctx.getBean("userDao", UserDao.class);

        post = new Post();
        user = new User();

        ArrayList<String> userAuthorities = new ArrayList<>();
        
        userAuthorities.add("ROLE_ADMIN");
        userAuthorities.add("ROLE_USER");
        userAuthorities.add("ROLE_CONTENTMANAGER");
        

        user.setUserName("ThatGuy");
        user.setPassWord("1234");
        user.setIsActive(true);
        user.setIsBanned(false);
        user.setAuthorities(userAuthorities);
        userDao.createUser(user);

        post.setStartDate(LocalDate.of(2017, 7, 1));
        post.setEndDate(LocalDate.of(2017, 8, 1));
        post.setTitle("Hey its July");
        post.setPostBody("Thats a month that happens at least once a year i think");
        post.setUserId(user.getUserId());
        post.setUser(user);
        post.setApprovalStatus(1);
        post.setImageFlag(1);
        post.setTitleFlag(1);
        post.setBodyFlag(1);
        post.setStartDateFlag(1);
        post.setEndDateFlag(1);
        post.setAuthorFlag(1);
        post.setCategoryFlag(1);

    }

    @After
    public void tearDown() {
        ArrayList<User> users = new ArrayList<>(userDao.actuallyReadAllUsers());
        for(User user : users){
            userDao.deleteAllAuthoritiesByUserName(user.getUserName());
            userDao.deleteUserByUserName(user.getUserName());
        }
    }

    /**
     * Test of createPost method, of class PostDaoImpl.
     */
    @Test
    public void testCreatePost() {
        postDao.createPost(post);
        Post fromDao = postDao.readPostById(post.getPostId());
        assertTrue(post.equals(fromDao));
    }

    /**
     * Test of readAllPosts method, of class PostDaoImpl.
     */
    @Test
    public void testReadAllPosts() {
        postDao.createPost(post);
        assertNotNull(postDao.readAllPosts(post.getPostId()));
    }

    /**
     * Test of updatePost method, of class PostDaoImpl.
     */
    @Test
    public void testUpdatePost() {
        postDao.createPost(post);
        post.setApprovalStatus(0);
        postDao.updatePost(post);
        Post fromDao = postDao.readPostById(post.getPostId());
        assertTrue(post.equals(fromDao));
    }

    /**
     * Test of deletePostById method, of class PostDaoImpl.
     */
    @Test
    public void testDeletePostById() {
        postDao.createPost(post);
        postDao.deletePostById(post.getPostId());
        post.setIsArchived(true);
        Post fromDao = postDao.readPostById(post.getPostId());
        assertTrue(post.equals(fromDao));

    }

    @Test
    public void testReadAllActivePosts() {
        postDao.createPost(post);
        assertNotNull(postDao.readAllActivePosts(0));
    }

}
