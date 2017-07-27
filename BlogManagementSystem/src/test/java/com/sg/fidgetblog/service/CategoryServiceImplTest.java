/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.service;

import com.sg.fidgetblog.dao.CategoryDao;
import com.sg.fidgetblog.dto.Category;
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
public class CategoryServiceImplTest {

    CategoryService categoryService;

    public CategoryServiceImplTest() {
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
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createCategory method, of class CategoryServiceImpl.
     */
    @Test
    public void testCreateCategory() {
        Category category = new Category();
        category.setCategoryId("#NewCategory");
        categoryService.createCategory(category);
        Category fromService = categoryService.readCategoryById(category.getCategoryId());
        assertEquals(fromService, category);
    }

    /**
     * Test of deleteCategoryById method, of class CategoryServiceImpl.
     */
    @Test
    public void testDeleteCategoryById() {
        Category category = new Category();
        category.setCategoryId("#ToBeDeleted");
        categoryService.createCategory(category);
        assertEquals(category, categoryService.readCategoryById(category.getCategoryId()));
        categoryService.deleteCategoryById(category.getCategoryId());
        assertNull(categoryService.readCategoryById(category.getCategoryId()));
    }

}
