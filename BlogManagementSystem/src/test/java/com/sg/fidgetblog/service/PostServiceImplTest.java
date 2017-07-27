/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.service;

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
public class PostServiceImplTest {

    PostService postService;
    UserService userService;

    User user = new User();
    Post post = new Post();

    public PostServiceImplTest() {
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

        postService = ctx.getBean("postService", PostService.class);
        userService = ctx.getBean("userService", UserService.class);

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
        userService.createUser(user);

        post.setStartDate(LocalDate.of(2017, 7, 1));
        post.setEndDate(LocalDate.of(2017, 8, 1));
        post.setTitle("Hey its July");
        post.setPostBody("Thats a month that happens at least once a year i think");
        post.setUserId(user.getUserId());
        post.setUser(user);
        post.setApprovalStatus(7);
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
        ArrayList<User> users = new ArrayList<>(userService.actuallyReadAllUsers());
        for(User user : users){
            userService.deleteAllAuthoritiesByUserName(user.getUserName());
            userService.deleteUser(user.getUserName());
        }
    }

    /**
     * Test of createPost method, of class PostServiceImpl.
     */
    @Test
    public void testCreatePost() {
        postService.createPost(post);
        Post fromService = postService.readPostById(post.getPostId());
        assertTrue(post.equals(fromService));
    }

    /**
     * Test of readAllPosts method, of class PostServiceImpl.
     */
    @Test
    public void testReadAllPosts() {
        postService.createPost(post);
        assertNotNull(postService.readAllPosts(post.getPostId()));
    }

    /**
     * Test of updatePost method, of class PostServiceImpl.
     */
    @Test
    public void testUpdatePost() {
        postService.createPost(post);
        post.setTitle("no its fucken august");
        postService.updatePost(post);
        Post fromService = postService.readPostById(post.getPostId());
        assertTrue(post.equals(fromService));
    }

    /**
     * Test of deletePostById method, of class PostServiceImpl.
     */
    @Test
    public void testDeletePostById() {
        postService.createPost(post);
        postService.deletePostById(post.getPostId());
        post.setIsArchived(true);
        Post fromService = postService.readPostById(post.getPostId());
        assertTrue(post.equals(fromService));

    }

    @Test
    public void testReadAllActivePosts() {
        postService.createPost(post);
        assertNotNull(postService.readAllActivePosts(post.getPostId()));
        postService.deletePostById(post.getPostId());
        assertFalse(postService.readAllActivePosts(post.getPostId()).contains(postService.readPostById(post.getPostId())));
    }

}
