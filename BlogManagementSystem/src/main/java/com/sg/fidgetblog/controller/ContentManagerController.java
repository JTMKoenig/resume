/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.controller;

import com.sg.fidgetblog.dto.AdminEditPost;
import com.sg.fidgetblog.dto.NewPost;
import com.sg.fidgetblog.dto.Post;
import com.sg.fidgetblog.dto.User;
import com.sg.fidgetblog.service.PostService;
import com.sg.fidgetblog.service.UserService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author vincentsiciliano
 */
@Controller
public class ContentManagerController {

    UserService userService;
    PostService postService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Inject
    public ContentManagerController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;

    }

    /////CONTENT MANAGER PRIVILEGE METHODS
    @RequestMapping(value = "/contentmanagerhome", method = RequestMethod.GET)
    public String contentManagerHome(Model model) {

        List<Post> postList = postService.readAllPosts(0);

        model.addAttribute("postList", postList);

        return "contentmanagerhome";

    }

    @RequestMapping(value = "/contentmanager-deletepost/{id}", method = RequestMethod.POST)
    public String contentManagerRemovePost(@PathVariable("id") String postId) {

        postService.deletePostById(Integer.parseInt(postId));

        return "redirect:/contentmanagerhome";

    }

    @RequestMapping(value = "/contentmanagerpostform/{id}", method = RequestMethod.GET)
    public String contentManagerPostForm(Model model, @PathVariable("id") String postId) {

        Post post = postService.readPostById(Integer.parseInt(postId));

        model.addAttribute("postId", post.getPostId());
        model.addAttribute("userId", post.getUser().getUserId());

        model.addAttribute("title", post.getTitle());
        model.addAttribute("userName", post.getUser().getUserName());
        model.addAttribute("postBody", post.getPostBody());
        model.addAttribute("startDate", post.getStartDate());
        model.addAttribute("endDate", post.getEndDate());

        model.addAttribute("imageFlag", post.getImageFlag());
        model.addAttribute("titleFlag", post.getTitleFlag());
        model.addAttribute("authorFlag", post.getAuthorFlag());
        model.addAttribute("bodyFlag", post.getBodyFlag());
        model.addAttribute("startDateFlag", post.getStartDateFlag());
        model.addAttribute("endDateFlag", post.getEndDateFlag());

        List<User> userList = userService.readAllUsers(0);

        model.addAttribute("userList", userList);

        return "cm-post-form";

    }

}
