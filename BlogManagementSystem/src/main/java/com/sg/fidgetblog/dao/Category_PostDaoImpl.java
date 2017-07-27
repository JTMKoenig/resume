/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dao;

import com.sg.fidgetblog.dto.Category;
import com.sg.fidgetblog.dto.Category_Post;
import com.sg.fidgetblog.dto.Post;
import com.sg.fidgetblog.dto.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author jono
 */
public class Category_PostDaoImpl implements Category_PostDao {

    JdbcTemplate jdbcTemplate;

    public Category_PostDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /////INSERT
    private static final String SQL_INSERT_CATEGORY_POST = "insert into Category_Post(CategoryId, PostId) values(?,?)";

    /////SELECT
    private static final String SQL_SELECT_CATEGORY_FROM_CATEGORY_POST = "select * from Category_Post where CategoryId = ?";
    private static final String SQL_SELECT_POST_FROM_CATEGORY_POST = "select * from Category_Post where PostId = ?";
    private static final String SQL_SELECT_CATEGORY_POST_BY_ID = "select * from Category_Post where Category_PostId = ?";

    private static final String SQL_SELECT_CATEGORY = "select * from Category where CategoryId = ?";
    private static final String SQL_SELECT_POST = "select * from Post where PostId = ?";
    private static final String SQL_SELECT_USER = "select * from User where UserId = ?";

    ////EDIT
    private static final String SQL_UPDATE_CATEGORY_POST = "update Category_Post set "
            + "CategoryId = ?, PostId = ? where Category_PostId = ?";

    ////DELETE
    private static final String SQL_DELETE_FROM_CATEGORY_POST = "delete from Category_Post "
            + "where Category_PostId = ?";

    private static final String SQL_SELECT_AUTHORITIES = "select * from Authorities where UserName = ?";

    @Override
    public void createCategory_Post(Category_Post categoryPost) {
        jdbcTemplate.update(SQL_INSERT_CATEGORY_POST,
                categoryPost.getCategoryId(),
                categoryPost.getPostId());
        categoryPost.setCategoryPostId(jdbcTemplate.queryForObject("select last_insert_id()", int.class));
    }

    @Override
    public Category_Post readCategory_PostById(int categoryPostId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_CATEGORY_POST_BY_ID,
                    new Category_PostMapper(jdbcTemplate), categoryPostId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    @Override
    public List<Category_Post> readCategory_PostsByPostId(int postId) {
        try {
            return jdbcTemplate.query(SQL_SELECT_POST_FROM_CATEGORY_POST,
                    new Category_PostMapper(jdbcTemplate), postId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateCategory_Post(Category_Post categoryPost) {
        jdbcTemplate.update(SQL_UPDATE_CATEGORY_POST, categoryPost.getCategoryId(),
                categoryPost.getPostId(), categoryPost.getCategoryPostId());
    }

    @Override
    public void deleteCategory_PostById(int categoryPostId) {
        jdbcTemplate.update(SQL_DELETE_FROM_CATEGORY_POST, categoryPostId);
    }

    private static final class Category_PostMapper implements RowMapper<Category_Post> {

        private JdbcTemplate jdbcTemplate;

        @Inject
        public Category_PostMapper(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        @Override
        public Category_Post mapRow(ResultSet rs, int i) throws SQLException {
            Category_Post category_Post = new Category_Post();
            category_Post.setCategoryPostId(rs.getInt("Category_PostId"));
            category_Post.setCategoryId(rs.getString("CategoryId"));
            category_Post.setPostId(rs.getInt("PostId"));

            try {
                Category category = jdbcTemplate.queryForObject(SQL_SELECT_CATEGORY, new CategoryMapper(jdbcTemplate), rs.getString("CategoryId"));
                category_Post.setCategory(category);

                Post post = jdbcTemplate.queryForObject(SQL_SELECT_POST, new PostMapper(jdbcTemplate), rs.getInt("PostId"));
                category_Post.setPost(post);
            } catch (EmptyResultDataAccessException e) {
            }
            return category_Post;
        }
    }

    private static final class CategoryMapper implements RowMapper<Category> {

        private JdbcTemplate jdbcTemplate;

        @Inject
        public CategoryMapper(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        @Override
        public Category mapRow(ResultSet rs, int i) throws SQLException {
            Category category = new Category();
            category.setCategoryId(rs.getString("CategoryId"));
            return category;
        }
    }

    private static final class PostMapper implements RowMapper<Post> {

        private JdbcTemplate jdbcTemplate;

        public PostMapper(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        @Override
        public Post mapRow(ResultSet rs, int i) throws SQLException {
            Post post = new Post();
            post.setPostId(rs.getInt("PostId"));
            post.setStartDate(LocalDate.parse(rs.getString("StartDate"), formatter));
            try {
                post.setEndDate(LocalDate.parse(rs.getString("EndDate"), formatter));
            } catch (NullPointerException e) {

            }
            post.setTitle(rs.getString("Title"));
            post.setPostBody(rs.getString("PostBody"));
            post.setUserId(rs.getInt("UserId"));
            post.setIsArchived(rs.getBoolean("isArchived"));
            post.setApprovalStatus(rs.getInt("ApprovalStatus"));
            post.setTitleFlag(rs.getInt("TitleFlag"));
            post.setBodyFlag(rs.getInt("BodyFlag"));
            post.setStartDateFlag(rs.getInt("StartDateFlag"));
            post.setEndDateFlag(rs.getInt("EndDateFlag"));
            post.setAuthorFlag(rs.getInt("AuthorFlag"));
            post.setCategoryFlag(rs.getInt("CategoryFlag"));
            post.setImageFlag(rs.getInt("ImageFlag"));

            try {
                User user = jdbcTemplate.queryForObject(SQL_SELECT_USER, new UserMapper(), rs.getInt("UserId"));
                user.setAuthorities(new ArrayList<>(jdbcTemplate.query(SQL_SELECT_AUTHORITIES, new AuthorityMapper(), user.getUserName())));
                post.setUser(user);

            } catch (SQLException | DataAccessException e) {
            }
            return post;
        }
    }

    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {

            User user = new User();
            user.setUserId(rs.getInt("UserID"));
            user.setUserName(rs.getString("UserName"));
            user.setPassWord(rs.getString("password"));
            user.setIsActive(rs.getBoolean("IsActive"));

            return user;

        }

    }

    private static final class AuthorityMapper implements RowMapper<String> {

        @Override
        public String mapRow(ResultSet rs, int i) throws SQLException {
            return rs.getString("Authority");
        }

    }

}
