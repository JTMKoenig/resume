/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dao;

import com.sg.fidgetblog.dto.Category_Post;
import java.util.List;

/**
 *
 * @author jono
 */
public interface Category_PostDao {

    public void createCategory_Post(Category_Post categoryPost);

    public Category_Post readCategory_PostById(int categoryPostId);
    
    public List<Category_Post> readCategory_PostsByPostId(int postId);

    public void updateCategory_Post(Category_Post categoryPost);

    public void deleteCategory_PostById(int categoryPostId);

}
