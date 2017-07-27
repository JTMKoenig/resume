/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.service;

import com.sg.fidgetblog.dto.NewPost;
import com.sg.fidgetblog.dto.Post;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jono
 */
public interface PostService {

    public void createPost(Post post);

    public Post readPostById(int postId);

    public void updatePost(Post post);

    public void deletePostById(int postId);

    public List<Post> readAllPosts(int selectNum);

    public List<Post> readAllActivePosts(int selectNum);

    public Map<String, String> convertFlagsToText(Post post);

    public List<Post> readPostsByCategoryId(String categoryId, int selectNum);

    public void setPostEndDate(NewPost newPost, Post post);
}
