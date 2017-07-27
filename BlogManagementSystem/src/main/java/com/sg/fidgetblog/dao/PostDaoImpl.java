/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dao;

import com.sg.fidgetblog.dto.Post;
import com.sg.fidgetblog.dto.StaticPage;
import com.sg.fidgetblog.dto.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author jono
 */
public class PostDaoImpl implements PostDao {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final String SQL_INSERT_POST
            = "insert into Post (StartDate, EndDate, Title, PostBody, UserId) "
            + "values(?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_POST
            = "update Post set isArchived = 1 where PostId = ?";

    private static final String SQL_UPDATE_POST
            = "update Post set StartDate = ?, EndDate = ?, Title = ?, "
            + "PostBody = ?, UserId = ?, isArchived = ?, ApprovalStatus = ?, "
            + "TitleFlag = ?, BodyFlag = ?, StartDateFlag = ?,"
            + "EndDateFlag = ?, AuthorFlag = ?, CategoryFlag = ?, ImageFlag = ? "
            + "where PostId = ?";

    private static final String SQL_SELECT_POST
            = "select * from Post where PostId = ?";

    private static final String SQL_SELECT_USER
            = "select * from User where UserId = ?";

    private static final String SQL_SELECT_POST_BY_USER_ID
            = "select * from Post where UserId=?";
    
    private static final String SQL_GET_USER_POST_COUNT 
            = "select count(*) from Post where UserId=?";

    private static final String SQL_SELECT_ALL_POSTS = "select * from Post order by Post.PostId desc limit ?,10";

    private static final String SQL_SELECT_ACTIVE_POSTS
            = "select * from Post where isArchived = 0 and StartDate <= CURRENT_DATE() and (EndDate >= CURRENT_DATE() or EndDate is null) order by Post.StartDate desc limit ?,20";

    private static final String SQL_SELECT_AUTHORITIES = "select * from Authorities where UserName = ?";

    private static final String SQL_SELECT_20_POSTS_BY_CATEGORY
            = "SELECT Post.* FROM Post "
            + "inner join Category_Post on Category_Post.PostId=Post.PostId "
            + "inner join Category on Category.CategoryId=Category_Post.CategoryId "
            + "where Category_Post.CategoryId = ? order by Post.StartDate desc limit ?,20; ";

    private JdbcTemplate jdbcTemplate;

    public PostDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createPost(Post post) {
        if (post.getEndDate() != null) {
            jdbcTemplate.update(SQL_INSERT_POST, post.getStartDate().format(formatter), post.getEndDate().format(formatter), post.getTitle(),
                    post.getPostBody(), post.getUser().getUserId());
        } else {
            jdbcTemplate.update(SQL_INSERT_POST, post.getStartDate().format(formatter), null, post.getTitle(),
                    post.getPostBody(), post.getUser().getUserId());
        }
        int postId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        post.setPostId(postId);

    }

    @Override
    public Post readPostById(int postId) {
        return jdbcTemplate.queryForObject(SQL_SELECT_POST, new PostMapper(jdbcTemplate), postId);
    }

    @Override
    public List<Post> readAllPosts(int selectNum) {
        return jdbcTemplate.query(SQL_SELECT_ALL_POSTS, new PostMapper(jdbcTemplate), selectNum);
    }

    @Override
    public List<Post> readAllActivePosts(int selectNum) {
        return jdbcTemplate.query(SQL_SELECT_ACTIVE_POSTS, new PostMapper(jdbcTemplate), selectNum);
    }
    
    public int getUserPostCount(int userId){
        return jdbcTemplate.queryForObject(SQL_GET_USER_POST_COUNT, new Object[userId], Integer.class);
    }

    @Override
    public void updatePost(Post post) {
        if (post.getEndDate() != null) {
            jdbcTemplate.update(SQL_UPDATE_POST, post.getStartDate().format(formatter), post.getEndDate().format(formatter), post.getTitle(),
                    post.getPostBody(), post.getUser().getUserId(), post.isIsArchived(), post.getApprovalStatus(),
                    post.getTitleFlag(), post.getBodyFlag(), post.getStartDateFlag(), post.getEndDateFlag(),
                    post.getAuthorFlag(), post.getCategoryFlag(), post.getImageFlag(), post.getPostId());
        } else {
            jdbcTemplate.update(SQL_UPDATE_POST, post.getStartDate().format(formatter), null, post.getTitle(),
                    post.getPostBody(), post.getUser().getUserId(), post.isIsArchived(), post.getApprovalStatus(),
                    post.getTitleFlag(), post.getBodyFlag(), post.getStartDateFlag(), post.getEndDateFlag(),
                    post.getAuthorFlag(), post.getCategoryFlag(), post.getImageFlag(), post.getPostId());
        }
    }

    @Override
    public void deletePostById(int postId) {
        jdbcTemplate.update(SQL_DELETE_POST, postId);
    }

    @Override
    public List<Post> readPostsByCategoryId(String categoryId, int selectNum) {
        return jdbcTemplate.query(SQL_SELECT_20_POSTS_BY_CATEGORY, new PostMapper(jdbcTemplate), categoryId, selectNum);
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
            user.setUserId(rs.getInt("UserId"));
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
