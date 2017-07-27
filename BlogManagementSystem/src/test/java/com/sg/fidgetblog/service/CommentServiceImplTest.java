/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.service;

import com.sg.fidgetblog.dto.Comment;
import com.sg.fidgetblog.dto.Post;
import com.sg.fidgetblog.dto.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Jack
 */
public class CommentServiceImplTest {

    CommentService commentService;
    PostService postService;
    UserService userService;

    Post post = new Post();
    User postUser = new User();
    User commentUser = new User();
    Comment comment = new Comment();

    public CommentServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        postService = ctx.getBean("postService", PostService.class);
        userService = ctx.getBean("userService", UserService.class);
        commentService = ctx.getBean("commentService", CommentService.class);

        post = new Post();
        postUser = new User();
        commentUser = new User();
        comment = new Comment();
        
        ArrayList<String> postUserAuthorities = new ArrayList<>();
        
        postUserAuthorities.add("ROLE_ADMIN");
        postUserAuthorities.add("ROLE_USER");
        postUserAuthorities.add("ROLE_CONTENTMANAGER");

        postUser.setUserName("adminGuy");
        postUser.setPassWord("1234");
        postUser.setIsActive(true);
        postUser.setIsBanned(false);
        postUser.setAuthorities(postUserAuthorities);
        userService.createUser(postUser);
        
        ArrayList<String> commentUserAuthorities = new ArrayList<>();
        
        commentUserAuthorities.add("ROLE_USER");

        commentUser.setUserName("shitPosterGuy");
        commentUser.setPassWord("shitpost");
        commentUser.setIsActive(true);
        commentUser.setIsBanned(false);
        commentUser.setAuthorities(commentUserAuthorities);
        userService.createUser(commentUser);

        post.setStartDate(LocalDate.of(2017, 7, 1));
        post.setEndDate(LocalDate.of(2017, 8, 1));
        post.setTitle("Hey its July");
        post.setPostBody("Thats a month that happens at least once a year i think");
        post.setUserId(postUser.getUserId());
        post.setUser(postUser);
        post.setApprovalStatus(1);
        post.setImageFlag(1);
        post.setTitleFlag(1);
        post.setBodyFlag(1);
        post.setStartDateFlag(1);
        post.setEndDateFlag(1);
        post.setAuthorFlag(1);
        post.setCategoryFlag(1);
        postService.createPost(post);

        comment.setPostId(post.getPostId());
        comment.setPost(post);
        comment.setUserId(commentUser.getUserId());
        comment.setUser(commentUser);
        comment.setCommentDate(LocalDateTime.of(2017, 7, 5, 6, 30));
        comment.setCommentBody("HEHEHEHE GOOD MEME THIS IS A SHITPOST");
        comment.setIsArchive(false);

    }

    @After
    public void tearDown() {
        ArrayList<User> users = new ArrayList<>(userService.actuallyReadAllUsers());
        for(User user : users){
            userService.deleteAllAuthoritiesByUserName(user.getUserName());
        }
    }

    /**
     * Test of createComment method, of class CommentServiceImpl.
     */
    @Test
    public void testCreateComment() {
        commentService.createComment(comment);
        Comment fromService = commentService.readCommentById(comment.getCommentId());
        assertTrue(comment.equals(fromService));
    }

    /**
     * Test of updateComment method, of class CommentServiceImpl.
     */
    @Test
    public void testUpdateComment() {
        commentService.createComment(comment);
        comment.setCommentBody("nvm im trying to be more respectful");
        commentService.updateComment(comment);
        assertTrue(comment.equals(commentService.readCommentById(comment.getCommentId())));
    }

    /**
     * Test of deleteCommentById method, of class CommentServiceImpl.
     */
    @Test
    public void testDeleteCommentById() {
        commentService.createComment(comment);
        comment.setIsArchive(true);
        commentService.deleteComment(comment.getCommentId());
        assertTrue(comment.equals(commentService.readCommentById(comment.getCommentId())));
    }

}
