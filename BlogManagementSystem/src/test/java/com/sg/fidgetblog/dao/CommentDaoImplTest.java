/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dao;

import com.sg.fidgetblog.dto.Comment;
import com.sg.fidgetblog.dto.Post;
import com.sg.fidgetblog.dto.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class CommentDaoImplTest {

    CommentDao commentDao;
    PostDao postDao;
    UserDao userDao;

    Post post = new Post();
    User postUser = new User();
    User commentUser = new User();
    Comment comment = new Comment();

    public CommentDaoImplTest() {
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

        postDao = ctx.getBean("postDao", PostDao.class);
        userDao = ctx.getBean("userDao", UserDao.class);
        commentDao = ctx.getBean("commentDao", CommentDao.class);

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
        userDao.createUser(postUser);
        
        ArrayList<String> commentUserAuthorities = new ArrayList<>();
        
        commentUserAuthorities.add("ROLE_USER");

        commentUser.setUserName("shitPosterGuy");
        commentUser.setPassWord("shitpost");
        commentUser.setIsActive(true);
        commentUser.setIsBanned(false);
        commentUser.setAuthorities(commentUserAuthorities);
        userDao.createUser(commentUser);

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
        postDao.createPost(post);

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
        ArrayList<User> users = new ArrayList<>(userDao.actuallyReadAllUsers());
        for(User user : users){
            userDao.deleteAllAuthoritiesByUserName(user.getUserName());
        }
    }

    /**
     * Test of createComment method, of class CommentDaoImpl.
     */
    @Test
    public void testCreateComment() {
        commentDao.createComment(comment);
        Comment fromDao = commentDao.readCommentById(comment.getCommentId());
        assertTrue(comment.equals(fromDao));
    }

    /**
     * Test of updateComment method, of class CommentDaoImpl.
     */
    @Test
    public void testUpdateComment() {
        commentDao.createComment(comment);
        comment.setCommentBody("nvm im trying to be more respectful");
        commentDao.updateComment(comment);
        assertTrue(comment.equals(commentDao.readCommentById(comment.getCommentId())));
    }

    /**
     * Test of deleteCommentById method, of class CommentDaoImpl.
     */
    @Test
    public void testDeleteCommentById() {
        commentDao.createComment(comment);
        comment.setIsArchive(true);
        commentDao.deleteCommentById(comment.getCommentId());
        Comment fromDao = commentDao.readCommentById(comment.getCommentId());
        assertTrue(comment.equals(fromDao));
    }

}
