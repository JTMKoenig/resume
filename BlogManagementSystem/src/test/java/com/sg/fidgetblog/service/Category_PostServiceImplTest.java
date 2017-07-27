/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.service;

import com.sg.fidgetblog.dto.Category;
import com.sg.fidgetblog.dto.Category_Post;
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
public class Category_PostServiceImplTest {

    CategoryService categoryService;
    PostService postService;
    UserService userService;
    Category_PostService categoryPostService;

    Category category = new Category();
    Post post = new Post();
    User user = new User();
    Category_Post categoryPost = new Category_Post();

    public Category_PostServiceImplTest() {
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

        categoryService = ctx.getBean("categoryService", CategoryService.class);
        postService = ctx.getBean("postService", PostService.class);
        userService = ctx.getBean("userService", UserService.class);
        categoryPostService = ctx.getBean("categoryPostService", Category_PostService.class);

        user = new User();
        post = new Post();
        category = new Category();
        categoryPost = new Category_Post();

        ArrayList<String> postUserAuthorities = new ArrayList<>();
        
        postUserAuthorities.add("ROLE_ADMIN");
        postUserAuthorities.add("ROLE_USER");
        postUserAuthorities.add("ROLE_CONTENTMANAGER");
        
        user.setUserName("ThatGuy");
        user.setPassWord("1234");
        user.setIsActive(true);
        user.setIsBanned(false);
        user.setAuthorities(postUserAuthorities);
        userService.createUser(user);

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
        postService.createPost(post);

        category.setCategoryId("#NewCategory");
        categoryService.createCategory(category);

        categoryPost.setCategoryId(category.getCategoryId());
        categoryPost.setCategory(category);
        categoryPost.setPostId(post.getPostId());
        categoryPost.setPost(post);
    }

    @After
    public void tearDown() {
        ArrayList<User> users = new ArrayList<>(userService.actuallyReadAllUsers());
        for(User user : users){
            userService.deleteAllAuthoritiesByUserName(user.getUserName());
        }
    }

    /**
     * Test of createCategory_Post method, of class Category_PostServiceImpl.
     */
    @Test
    public void testCreateCategory_Post() {
        categoryPostService.createCategory_Post(categoryPost);
        Category_Post fromService = categoryPostService.readCategory_PostById(categoryPost.getCategoryPostId());
        assertTrue(categoryPost.equals(fromService));
    }

    /**
     * Test of updateCategory_Post method, of class Category_PostServiceImpl.
     */
    @Test
    public void testUpdateCategory_Post() {
        categoryPostService.createCategory_Post(categoryPost);
        Category update = new Category();
        update.setCategoryId("newStuff");
        categoryService.createCategory(update);
        categoryPost.setCategory(update);
        categoryPost.setCategoryId(update.getCategoryId());
        categoryPostService.updateCategory_Post(categoryPost);
        assertTrue(categoryPost.equals(categoryPostService.readCategory_PostById(categoryPost.getCategoryPostId())));
    }

    /**
     * Test of deleteCategory_PostById method, of class
     * Category_PostServiceImpl.
     */
    @Test
    public void testDeleteCategory_PostById() {
        categoryPostService.createCategory_Post(categoryPost);
        assertTrue(categoryPost.equals(categoryPostService.readCategory_PostById(categoryPost.getCategoryPostId())));
        categoryPostService.deleteCategory_PostById(categoryPost.getCategoryPostId());
        assertNull(categoryPostService.readCategory_PostById(categoryPost.getCategoryPostId()));
    }

}
