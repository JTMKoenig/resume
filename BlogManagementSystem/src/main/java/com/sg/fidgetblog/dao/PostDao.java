/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dao;

import com.sg.fidgetblog.dto.Post;
import java.util.List;

/**
 *
 * @author jono
 */
public interface PostDao {

    public void createPost(Post post);

    public Post readPostById(int postId);

    public void updatePost(Post post);

    public void deletePostById(int postId);

    public List<Post> readAllPosts(int selectNum);

    public List<Post> readAllActivePosts(int selectNum);
    
    public List<Post> readPostsByCategoryId(String categoryId, int selectNum);
}
