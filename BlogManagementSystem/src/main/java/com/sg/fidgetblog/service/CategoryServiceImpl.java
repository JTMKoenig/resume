/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.service;

import com.sg.fidgetblog.dao.CategoryDao;
import com.sg.fidgetblog.dto.Category;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jono
 */
public class CategoryServiceImpl implements CategoryService {

    CategoryDao categoryDao;

    @Inject
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public void createCategory(Category category) {
        categoryDao.createCategory(category);
    }

    @Override
    public Category readCategoryById(String categoryId) {
        return categoryDao.readCategoryById(categoryId);
    }

    @Override
    public void deleteCategoryById(String categoryId) {
        categoryDao.deleteCategoryById(categoryId);
    }

    @Override
    public String[] readAllCategories() {
        List<Category> categoriesList = categoryDao.readAllCategories();
        List<String> categoriesStringList = new ArrayList();
        for (Category i : categoriesList) {
            String category = i.getCategoryId();
            categoriesStringList.add(category);
        }

        String[] categoriesArray = categoriesStringList.toArray(new String[0]);
        return categoriesArray;
    }

    @Override
    public void createCategoriesFromArray(String[] newPostCategories) {
        for (int i = 0; i < newPostCategories.length; i++) {
            Category newCategory = new Category();
            newCategory.setCategoryId(newPostCategories[i]);
            categoryDao.createCategory(newCategory);
        }
    }

    @Override
    public List<Category> getCategoriesByPostId(int postId) {
        return categoryDao.readAllCategoriesByPostId(postId);
    }
    
    

}
