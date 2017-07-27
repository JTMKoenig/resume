/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.service;

import com.sg.fidgetblog.dao.PostDao;
import com.sg.fidgetblog.dto.NewPost;
import com.sg.fidgetblog.dto.Post;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author jono
 */
public class PostServiceImpl implements PostService {
    
    PostDao postDao;
    
    @Inject
    public PostServiceImpl(PostDao postDao) {
        this.postDao = postDao;
    }
    
    @Override
    public void createPost(Post post) {
        postDao.createPost(post);
    }
    
    @Override
    public Post readPostById(int postId) {
        
        Post post = postDao.readPostById(postId);
        
        int approvalStatus = post.getAuthorFlag() + post.getBodyFlag() + post.getCategoryFlag() + post.getEndDateFlag() + post.getImageFlag() + post.getStartDateFlag() + post.getTitleFlag();
        post.setApprovalStatus(approvalStatus);
        
        return post;
    }
    
    @Override
    public void updatePost(Post post) {
        postDao.updatePost(post);
    }
    
    @Override
    public void deletePostById(int postId) {
        postDao.deletePostById(postId);
    }
    
    @Override
    public List<Post> readAllPosts(int selectNum) {
        List<Post> postList = postDao.readAllPosts(selectNum);
        
        for (Post post : postList) {
            
            int approvalStatus = post.getAuthorFlag() + post.getBodyFlag() + post.getCategoryFlag() + post.getEndDateFlag() + post.getImageFlag() + post.getStartDateFlag() + post.getTitleFlag();
            post.setApprovalStatus(approvalStatus);
            
            if (post.getAuthorFlag() == 1 && post.getCategoryFlag() == 1 && post.getBodyFlag() == 1 && post.getEndDateFlag() == 1 && post.getImageFlag() == 1 && post.getStartDateFlag() == 1 && post.getTitleFlag() == 1) {
                post.setColorStatus("yellow");
            } else if (approvalStatus > 0) {
                post.setColorStatus("red");
            }
            
            if (approvalStatus == 0) {
                post.setColorStatus("green");
            }
            
            if (post.isIsArchived()) {
                post.setColorStatus("grey");
            }
            
        }
        
        return postList;
    }
    
    @Override
    public List<Post> readAllActivePosts(int selectNum) {
        
        return postDao.readAllActivePosts(selectNum);
    }
    
    @Override
    public Map<String, String> convertFlagsToText(Post post) {
        Map<String, Integer> flagNumberMap = new HashMap();
        Map<String, String> flagStringMap = new HashMap();
        
        flagNumberMap.put("titleFlag", post.getTitleFlag());
        flagNumberMap.put("authorFlag", post.getAuthorFlag());
        flagNumberMap.put("startDateFlag", post.getStartDateFlag());
        flagNumberMap.put("endDateFlag", post.getEndDateFlag());
        flagNumberMap.put("bodyFlag", post.getBodyFlag());
        flagNumberMap.put("imageFlag", post.getImageFlag());
        flagNumberMap.put("categoryFlag", post.getCategoryFlag());
        
        for (Map.Entry entry : flagNumberMap.entrySet()) {
            if ((int) entry.getValue() == 0) {
                flagStringMap.put((String) entry.getKey(), "Approved");
            }
            if ((int) entry.getValue() == 1) {
                flagStringMap.put((String) entry.getKey(), "Needs Review");
            }
            if ((int) entry.getValue() == 2) {
                flagStringMap.put((String) entry.getKey(), "Flagged");
            }
        }
        return flagStringMap;
        
    }
    
    @Override
    public List<Post> readPostsByCategoryId(String categoryId, int selectNum) {
        return postDao.readPostsByCategoryId(categoryId, selectNum);
    }

    @Override
    public void setPostEndDate(NewPost newPost, Post post) {
        if (newPost.getNewPostEndDate() != null) {
            post.setEndDate(newPost.getNewPostEndDate());
        }
    }
    
}
