/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.controller;

import com.sg.fidgetblog.dto.Category;
import com.sg.fidgetblog.dto.Comment;
import com.sg.fidgetblog.dto.Graphic;
import com.sg.fidgetblog.dto.NewPost;
import com.sg.fidgetblog.dto.Post;
import com.sg.fidgetblog.dto.User;
import com.sg.fidgetblog.service.CategoryService;
import com.sg.fidgetblog.service.Category_PostService;
import com.sg.fidgetblog.service.CommentService;
import com.sg.fidgetblog.service.GraphicService;
import com.sg.fidgetblog.service.PostService;
import com.sg.fidgetblog.service.UserService;
import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author vincentsiciliano
 */
@Controller
public class PostController {

    UserService userService;
    PostService postService;
    CategoryService categoryService;
    Category_PostService categoryPostService;
    CommentService commentService;
    GraphicService graphicService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Inject
    public PostController(UserService userService, PostService postService,
            CategoryService categoryService, Category_PostService categoryPostService,
            CommentService commentService, GraphicService graphicService) {
        this.userService = userService;
        this.postService = postService;
        this.categoryService = categoryService;
        this.categoryPostService = categoryPostService;
        this.commentService = commentService;
        this.graphicService = graphicService;
    }

    @RequestMapping(value = "/viewpost/{id}", method = RequestMethod.GET)
    public String viewPost(@PathVariable("id") int postId, Model model) {

        Post post = postService.readPostById(postId);
        String postDate = post.getStartDate().format(formatter);
        List<Comment> comments = commentService.getCommentsByPostId(String.valueOf(postId));
        List<Category> categories = categoryService.getCategoriesByPostId(postId);

        model.addAttribute("categories", categories);
        model.addAttribute("comments", comments);
        model.addAttribute("date", postDate);
        model.addAttribute("post", post);

        return "viewpost";
    }

    @RequestMapping(value = "/deletepost/{id}", method = RequestMethod.POST)
    public String removePost(@PathVariable("id") int postId) {

        postService.deletePostById(postId);

        return "redirect:/adminhome";
    }

    @RequestMapping(value = "/newpostform", method = RequestMethod.GET)
    public String newPostForm(Model model) {

        String[] allCategories = categoryService.readAllCategories();

        model.addAttribute("categories", allCategories);

        return "newpostform";
    }

    @RequestMapping(value = "/createpost", method = RequestMethod.POST)
    public String createPost(@RequestParam("newPostCategories[]") String[] newPostCategories,
            @ModelAttribute("newPost") NewPost newPost, Principal principal,
            @RequestParam("graphic") MultipartFile graphicFile) {

        //create post to fill using newPost
        Post post = new Post();

        User testUser = userService.readUserById(1);

        post.setUser(testUser);

        String currentUser = principal.getName();

        User user = userService.readUserByUsername(principal.getName());

        post.setUser(userService.readUserByUsername(principal.getName()));
        post.setStartDate(newPost.getNewPostStartDate());
        postService.setPostEndDate(newPost, post);
        post.setPostBody(newPost.getNewPostBody());
        post.setTitle(newPost.getNewPostTitle());

        postService.createPost(post);

        //save header graphic with postId
        Graphic newGraphic = new Graphic();
        newGraphic.setImageId(graphicService.createGraphic(graphicFile));
        newGraphic.setPost(post);
        graphicService.updateGraphicPostId(newGraphic);
        //take newPostCategories string and separate into hashtags
        //add categories to DB
        categoryService.createCategoriesFromArray(newPostCategories);
        //create and Add Category_Post Bridge items
        categoryPostService.createMultipleCategoryPosts(newPostCategories, post);

        return "redirect:/newpostform";
    }

    @RequestMapping(value = "/restorepost/{id}", method = RequestMethod.POST)
    public String restorePost(@PathVariable("id") String postId) {

        Post post = postService.readPostById(Integer.parseInt(postId));
        post.setIsArchived(false);
        postService.updatePost(post);

        return "redirect:/adminhome";
    }

}
