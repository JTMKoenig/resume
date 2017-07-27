/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.controller;

import com.sg.fidgetblog.dto.User;
import com.sg.fidgetblog.service.PostService;
import com.sg.fidgetblog.service.UserService;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author vincentsiciliano
 */
@Controller
public class UserController {

    UserService userService;
    PostService postService;
    PasswordEncoder encoder;

    @Inject
    public UserController(UserService userService, PostService postService, PasswordEncoder encoder) {
        this.userService = userService;
        this.postService = postService;
        this.encoder = encoder;

    }

    @RequestMapping(value = "/myaccount", method = RequestMethod.GET)
    public String myAccount() {

        return "myaccount";
    }

    @RequestMapping(value = "/createuser", method = RequestMethod.POST)
    public String createUser(HttpServletRequest request) {

        User newUser = new User();

        newUser.setIsActive(true);
        String clearPw = request.getParameter("passWord");
        String hashPw = encoder.encode(clearPw);
        newUser.setUserName(request.getParameter("userName"));
        newUser.setPassWord(hashPw);

        newUser.addAuthority("ROLE_USER");
        if (null != request.getParameter("isAdmin")) {
            newUser.addAuthority("ROLE_ADMIN");
        }

        userService.createUser(newUser);

        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm() {

        return "register";

    }

}
