/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dao;

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
public class Category_PostDaoImplTest {

    CategoryDao categoryDao;
    PostDao postDao;
    UserDao userDao;
    Category_PostDao categoryPostDao;

    Category category = new Category();
    Post post = new Post();
    User user = new User();
    Category_Post categoryPost = new Category_Post();

    public Category_PostDaoImplTest() {
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

        categoryDao = ctx.getBean("categoryDao", CategoryDao.class);
        postDao = ctx.getBean("postDao", PostDao.class);
        userDao = ctx.getBean("userDao", UserDao.class);
        categoryPostDao = ctx.getBean("categoryPostDao", Category_PostDao.class);

        user = new User();
        post = new Post();
        category = new Category();
        categoryPost = new Category_Post();
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
        postDao.createPost(post);

        category.setCategoryId("#NewCategory");
        categoryDao.createCategory(category);

        categoryPost.setCategoryId(category.getCategoryId());
        categoryPost.setCategory(category);
        categoryPost.setPostId(post.getPostId());
        categoryPost.setPost(post);
    }

    @After
    public void tearDown() {
        ArrayList<User> users = new ArrayList<>(userDao.actuallyReadAllUsers());
        for(User user : users){
            userDao.deleteAllAuthoritiesByUserName(user.getUserName());
        }
    }

    /**
     * Test of createCategory_Post method, of class Category_PostDaoImpl.
     */
    @Test
    public void testCreateCategory_Post() {
        categoryPostDao.createCategory_Post(categoryPost);
        Category_Post fromDao = categoryPostDao.readCategory_PostById(categoryPost.getCategoryPostId());
        assertTrue(categoryPost.equals(fromDao));
    }

    /**
     * Test of updateCategory_Post method, of class Category_PostDaoImpl.
     */
    @Test
    public void testUpdateCategory_Post() {
        categoryPostDao.createCategory_Post(categoryPost);
        Category update = new Category();
        update.setCategoryId("newStuff");
        categoryDao.createCategory(update);
        categoryPost.setCategory(update);
        categoryPost.setCategoryId(update.getCategoryId());
        categoryPostDao.updateCategory_Post(categoryPost);
        assertTrue(categoryPost.equals(categoryPostDao.readCategory_PostById(categoryPost.getCategoryPostId())));
    }

    /**
     * Test of deleteCategory_PostById method, of class Category_PostDaoImpl.
     */
    @Test
    public void testDeleteCategory_PostById() {
        categoryPostDao.createCategory_Post(categoryPost);
        assertTrue(categoryPost.equals(categoryPostDao.readCategory_PostById(categoryPost.getCategoryPostId())));
        categoryPostDao.deleteCategory_PostById(categoryPost.getCategoryPostId());
        assertNull(categoryPostDao.readCategory_PostById(categoryPost.getCategoryPostId()));
    }

}
