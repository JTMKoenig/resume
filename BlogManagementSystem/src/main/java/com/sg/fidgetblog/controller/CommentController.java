/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.controller;

import com.sg.fidgetblog.dto.Comment;
import com.sg.fidgetblog.dto.Post;
import com.sg.fidgetblog.dto.User;
import com.sg.fidgetblog.service.CommentService;
import com.sg.fidgetblog.service.UserService;
import java.security.Principal;
import java.time.LocalDateTime;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author vincentsiciliano
 */
@Controller
public class CommentController {

    CommentService commentService;
    UserService userService;

    @Inject
    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;

    }

    @RequestMapping(value = "/comment/login/{postId}", method = RequestMethod.GET)
    public String loginToPostComment(@PathVariable("postId") int postId, @RequestParam("comment") String comment, Model model) {

        model.addAttribute("comment", comment);

        return "redirect:/viewpost/" + postId;
    }

    @RequestMapping(value = "/comment/post/{postId}", method = RequestMethod.POST)
    public String postComment(@PathVariable("postId") int postId, @RequestParam("comment") String comment, Principal principal) {

        Comment newComment = new Comment();
        newComment.setCommentBody(comment);
        newComment.setCommentDate(LocalDateTime.now());
        Post commentPost = new Post();
        commentPost.setPostId(postId);
        newComment.setPost(commentPost);
        newComment.setPostId(postId);
        User currentUser = userService.readUserByUsername(principal.getName());
        newComment.setUser(currentUser);
        newComment.setUserId(currentUser.getUserId());
        newComment.setIsArchive(Boolean.FALSE);
        commentService.createComment(newComment);

        return "redirect:/viewpost/" + postId;
    }

    @RequestMapping(value = "/comment/delete/{commentId}/post/{postId}", method = RequestMethod.POST)
    public String deleteComment(@PathVariable("commentId") int commentId, @PathVariable("postId") int postId) {
        commentService.deleteComment(commentId);

        return "redirect:/viewpost/" + postId;
    }

}
