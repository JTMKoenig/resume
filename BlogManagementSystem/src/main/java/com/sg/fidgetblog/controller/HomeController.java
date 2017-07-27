/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.controller;

import com.sg.fidgetblog.dto.Post;
import com.sg.fidgetblog.service.PostService;
import com.sg.fidgetblog.service.StaticPageService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author vincentsiciliano
 */
@Controller
public class HomeController {

    PostService postService;
    

    @Inject
    public HomeController(PostService postService) {
        this.postService = postService;
        
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String startPage() {
        return "redirect:/home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {

        List<Post> postList = postService.readAllActivePosts(0);
        model.addAttribute("postList", postList);

        return "jinjahome";
    }

    @RequestMapping(value = "/home/page/{index}", method = RequestMethod.GET)
    public String homePagination(Model model, @PathVariable("index") int index) {

        int queryIndex = index * 20;

        List<Post> postList = postService.readAllActivePosts(queryIndex);

        model.addAttribute("postList", postList);
        model.addAttribute("queryIndex", queryIndex);
        model.addAttribute("index", index);

        return "jinjahome";
    }
    

    
}
