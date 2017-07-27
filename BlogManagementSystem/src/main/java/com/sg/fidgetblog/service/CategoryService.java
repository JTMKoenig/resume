/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.service;

import com.sg.fidgetblog.dto.Category;
import java.util.List;

/**
 *
 * @author jono
 */
public interface CategoryService {

    public void createCategory(Category category);

    public Category readCategoryById(String categoryId);

    public void deleteCategoryById(String categoryId);

    public String[] readAllCategories();

    public void createCategoriesFromArray(String[] newPostCategories);
    
    public List<Category> getCategoriesByPostId(int postId);

}
