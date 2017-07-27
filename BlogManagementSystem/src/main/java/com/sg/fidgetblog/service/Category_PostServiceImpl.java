/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.service;

import com.sg.fidgetblog.dao.Category_PostDao;
import com.sg.fidgetblog.dto.Category;
import com.sg.fidgetblog.dto.Category_Post;
import com.sg.fidgetblog.dto.Post;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jono
 */
public class Category_PostServiceImpl implements Category_PostService {

    Category_PostDao categoryPostDao;

    @Inject
    public Category_PostServiceImpl(Category_PostDao categoryPostDao) {
        this.categoryPostDao = categoryPostDao;
    }

    @Override
    public void createCategory_Post(Category_Post categoryPost) {
        categoryPostDao.createCategory_Post(categoryPost);
    }

    @Override
    public Category_Post readCategory_PostById(int categoryPostId) {
        return categoryPostDao.readCategory_PostById(categoryPostId);
    }
    
    @Override
    public List<Category_Post> readCategory_PostsByPostId(int postId){
        return categoryPostDao.readCategory_PostsByPostId(postId);
    }

    @Override
    public void updateCategory_Post(Category_Post categoryPost) {
        categoryPostDao.updateCategory_Post(categoryPost);
    }

    @Override
    public void deleteCategory_PostById(int categoryPostId) {
        categoryPostDao.deleteCategory_PostById(categoryPostId);
    }

    @Override
    public void createMultipleCategoryPosts(String[] newPostCategories, Post post) {
        for (String i : newPostCategories) {
            Category_Post categoryPost = new Category_Post();
            categoryPost.setCategoryId(i);
            categoryPost.setPostId(post.getPostId());
            categoryPostDao.createCategory_Post(categoryPost);

        }
    }

}
