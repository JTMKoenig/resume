/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dao;

import com.sg.fidgetblog.dto.Comment;
import java.util.List;

/**
 *
 * @author jono
 */
public interface CommentDao {

    public void createComment(Comment comment);

    public Comment readCommentById(int commentId);

    public void updateComment(Comment comment);

    public void deleteCommentById(int commentId);

    public List<Comment> getCommentsByPostId(String postId);
    
    public List<Comment> getAllComments(int selectNum);
}
