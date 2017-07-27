/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.controller;

import com.sg.fidgetblog.dto.AdminEditPost;
import com.sg.fidgetblog.dto.Category;
import com.sg.fidgetblog.dto.Category_Post;
import com.sg.fidgetblog.dto.NewPost;
import com.sg.fidgetblog.dto.Post;
import com.sg.fidgetblog.dto.StaticPage;
import com.sg.fidgetblog.dto.User;
import com.sg.fidgetblog.service.CategoryService;
import com.sg.fidgetblog.service.Category_PostService;
import com.sg.fidgetblog.service.PostService;
import com.sg.fidgetblog.service.StaticPageService;
import com.sg.fidgetblog.service.UserService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.security.Principal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author vincentsiciliano
 */
@CrossOrigin
@Controller
public class AdminController {

    UserService userService;
    PostService postService;
    StaticPageService staticPageService;
    Category_PostService categoryPostService;
    CategoryService categoryService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Inject
    public AdminController(UserService userService, PostService postService, StaticPageService staticPageService, Category_PostService categoryPostService, CategoryService categoryService) {
        this.userService = userService;
        this.postService = postService;
        this.staticPageService = staticPageService;
        this.categoryPostService = categoryPostService;
        this.categoryService=categoryService;

    }

    @RequestMapping(value = "/adminhome", method = RequestMethod.GET)
    public String adminHome(Model model, HttpServletRequest request) {

        List<Post> postList = postService.readAllPosts(0);

        model.addAttribute("postList", postList);

        List<User> userList = userService.readAllUsers(0);

        model.addAttribute("userList", userList);
        
        
        List<StaticPage> staticPageList = staticPageService.readAllStaticPage();

        model.addAttribute("staticPageList", staticPageList);
        
        
        

        return "adminhome";

    }

    @RequestMapping(value = "/adminpostform/{id}", method = RequestMethod.GET)
    public String adminPostForm(Model model, @PathVariable("id") String postId) {

        Post post = postService.readPostById(Integer.parseInt(postId));

        model.addAttribute("postId", post.getPostId());
        model.addAttribute("userId", post.getUser().getUserId());

        model.addAttribute("title", post.getTitle());
        model.addAttribute("userName", post.getUser().getUserName());
        model.addAttribute("postBody", post.getPostBody());
        model.addAttribute("startDate", post.getStartDate());
        model.addAttribute("endDate", post.getEndDate());
        List<Category_Post> categoryList = categoryPostService.readCategory_PostsByPostId(Integer.parseInt(postId));

        Map<String, String> flagStrings = postService.convertFlagsToText(post);
        model.addAttribute("flagMap", flagStrings);
        model.addAttribute("imageFlag", post.getImageFlag());
        model.addAttribute("titleFlag", post.getTitleFlag());
        model.addAttribute("authorFlag", post.getAuthorFlag());
        model.addAttribute("bodyFlag", post.getBodyFlag());
        model.addAttribute("startDateFlag", post.getStartDateFlag());
        model.addAttribute("endDateFlag", post.getEndDateFlag());
        model.addAttribute("categoryFlag", post.getCategoryFlag());

        List<User> userList = userService.readAllUsers(0);

        model.addAttribute("userList", userList);

        return "admin-post-form";

    }
    
    @RequestMapping(value = "/admincreatepost")
    public String adminCreatePost(@RequestParam("newPostCategories[]") String[] newPostCategories, @ModelAttribute("newPost") NewPost newPost, Principal principal){
        

        Post post = new Post();

        User user = userService.readUserByUsername(principal.getName());

        post.setUser(userService.readUserByUsername(principal.getName()));
        post.setStartDate(newPost.getNewPostStartDate());
        postService.setPostEndDate(newPost, post);
        post.setPostBody(newPost.getNewPostBody());
        post.setTitle(newPost.getNewPostTitle());
        
        post.setAuthorFlag(0);
        post.setBodyFlag(0);
        post.setCategoryFlag(0);
        post.setEndDateFlag(0);
        post.setImageFlag(0);
        post.setTitleFlag(0);
        post.setApprovalStatus(0);
        post.setColorStatus("green");
        

        postService.createPost(post);

        //take newPostCategories string and separate into hashtags
        //add categories to DB
        categoryService.createCategoriesFromArray(newPostCategories);
        //create and Add Category_Post Bridge items
        categoryPostService.createMultipleCategoryPosts(newPostCategories, post);
        
        return "redirect:/adminhome";
    }

    @RequestMapping(value = "/makeadminedit", method = RequestMethod.POST)
    public String makeAdminEdit(@ModelAttribute AdminEditPost adminEditPost) {

        Post post = new Post();

        post.setPostId(adminEditPost.getPostId());
        post.setUserId(Integer.parseInt(adminEditPost.getUserId()));
        post.setTitle(adminEditPost.getTitle());
        post.setPostBody(adminEditPost.getPostBody());
        post.setStartDate(LocalDate.parse(adminEditPost.getStartDate(), formatter));
        post.setEndDate(LocalDate.parse(adminEditPost.getEndDate(), formatter));
        post.setUser(userService.readUserById(Integer.parseInt(adminEditPost.getUserId())));
        

        post.setAuthorFlag(Integer.parseInt(adminEditPost.getAuthorFlag()));
        post.setBodyFlag(Integer.parseInt(adminEditPost.getBodyFlag()));
        post.setStartDateFlag(Integer.parseInt(adminEditPost.getStartDateFlag()));
        post.setEndDateFlag(Integer.parseInt(adminEditPost.getEndDateFlag()));
        post.setTitleFlag(Integer.parseInt(adminEditPost.getTitleFlag()));
        post.setImageFlag(Integer.parseInt(adminEditPost.getImageFlag()));

        post.setCategoryFlag(0);

        post.setApprovalStatus(0);
        post.setIsArchived(false);

        postService.updatePost(post);

        return "redirect:/adminhome";

    }

    @RequestMapping(value = "/staticpageform", method = RequestMethod.GET)
    public String staticPageForm(Model model) {

        return "staticpage-form";

    }

    @RequestMapping(value = "/createstaticpage", method = RequestMethod.POST)
    public String createStaticPage(@ModelAttribute StaticPage staticPage) {

        staticPageService.createStaticPage(staticPage);

        return "redirect:/adminhome";

    }

    @RequestMapping(value = "/changestaticpageorder", method = RequestMethod.POST)
    public String changeStaticPageOrder(HttpServletRequest request, Principal principal) {

        String[] staticPageOrderList = request.getParameterValues("staticPageOrderList");
        
        for(int i=0; i<staticPageOrderList.length; i++){
            
            StaticPage staticPage = staticPageService.readStaticPageById(Integer.parseInt(staticPageOrderList[i]));
            staticPage.setStaticPageIndex(i);
            staticPageService.updateStaticPage(staticPage);
        }
        
        
        
        


        return "redirect:/adminhome";

    }
    
    @RequestMapping(value = "/removestaticpage/{id}", method = RequestMethod.POST)
    public String removeStaticPage(@PathVariable("id") String postId, Principal principal) {
        
        staticPageService.deleteStaticPageById(Integer.parseInt(postId));
        
        return "redirect:/adminhome";
    }
    
    @RequestMapping(value = "/restorestaticpage/{id}", method = RequestMethod.POST)
    public String restoreStaticPage(@PathVariable("id") String postId, Principal principal) {
        
        staticPageService.restoreStaticPageById(Integer.parseInt(postId));
        
        return "redirect:/adminhome";
    }

}
