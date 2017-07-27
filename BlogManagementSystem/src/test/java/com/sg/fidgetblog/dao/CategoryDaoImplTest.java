/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dao;

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
public class CategoryDaoImplTest {

    CategoryDao dao;

    public CategoryDaoImplTest() {
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

        dao = ctx.getBean("categoryDao", CategoryDao.class);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createCategory method, of class CategoryDaoImpl.
     */
    @Test
    public void testCreateCategory() {
        Category category = new Category();
        category.setCategoryId("#NewCategory");
        dao.createCategory(category);
        Category fromDao = dao.readCategoryById(category.getCategoryId());
        assertEquals(fromDao, category);
    }

    /**
     * Test of updateCategory method, of class CategoryDaoImpl.
     */
    /**
     * Test of deleteCategoryById method, of class CategoryDaoImpl.
     */
    @Test
    public void testDeleteCategoryById() {
        Category category = new Category();
        category.setCategoryId("#ToBeDeleted");
        dao.createCategory(category);
        assertEquals(category, dao.readCategoryById(category.getCategoryId()));
        dao.deleteCategoryById(category.getCategoryId());
        assertNull(dao.readCategoryById(category.getCategoryId()));
    }

}
