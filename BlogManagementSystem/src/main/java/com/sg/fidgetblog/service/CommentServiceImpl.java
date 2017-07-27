/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.service;

import com.sg.fidgetblog.dao.CommentDao;
import com.sg.fidgetblog.dto.Comment;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jono
 */
public class CommentServiceImpl implements CommentService {

    CommentDao commentDao;

    @Inject
    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public void createComment(Comment comment) {
        commentDao.createComment(comment);
    }

    @Override
    public Comment readCommentById(int commentId) {
        return commentDao.readCommentById(commentId);
    }

    @Override
    public void updateComment(Comment comment) {
        commentDao.updateComment(comment);
    }

    @Override
    public void deleteComment(int commentId) {
        commentDao.deleteCommentById(commentId);
    }

    @Override
    public List<Comment> getCommentsByPostId(String postId) {
        return commentDao.getCommentsByPostId(postId);
    }

    @Override
    public List<Comment> getAllComments(int selectNum) {
        return commentDao.getAllComments(selectNum);
    }

}
