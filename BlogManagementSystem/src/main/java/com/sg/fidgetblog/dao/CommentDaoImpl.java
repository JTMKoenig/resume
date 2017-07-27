/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dao;

import static com.sg.fidgetblog.dao.PostDaoImpl.formatter;
import com.sg.fidgetblog.dto.Comment;
import com.sg.fidgetblog.dto.Post;
import com.sg.fidgetblog.dto.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class CommentDaoImpl implements CommentDao {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    /////INSERT
    private static final String SQL_INSERT_COMMENT = "insert into Comment (PostId, UserId, CommentDate, CommentBody, isArchive) value(?, ?, ?, ?, ?)";

    /////SELECT
    private static final String SQL_SELECT_COMMENT = "select * from Comment where CommentId = ?";
    private static final String SQL_SELECT_COMMENT_BY_POST_ID
            = "select * from Comment where PostId = ? and isArchive = 0 order by CommentDate desc";

    /////SELECT ALL
    private static final String SQL_SELECT_ALL_COMMENTS = "select * from Comment order by CommentId desc limit ?,10;";

    /////UPDATE
    private static final String SQL_UPDATE_COMMENT = "update Comment set PostId = ?, UserId = ?, CommentDate = ?, CommentBody = ?, isArchive = ? where CommentId = ?";

    /////DELETE
    private static final String SQL_DELETE_COMMENT = "update Comment set isArchive = 1 where CommentId = ?";

    private static final String SQL_SELECT_POST
            = "select * from Post where PostId = ?";

    private static final String SQL_SELECT_USER
            = "select * from User where UserId = ?";

    private static final String SQL_SELECT_POST_USER
            = "select * from User where PostId = ?";

    private static final String SQL_SELECT_AUTHORITIES = "select * from Authorities where UserName = ?";

    JdbcTemplate jdbcTemplate;

    @Inject
    public CommentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    @Override
    public void createComment(Comment comment) {
        jdbcTemplate.update(SQL_INSERT_COMMENT, comment.getPost().getPostId(), comment.getUser().getUserId(), comment.getCommentDate().format(timeFormatter), comment.getCommentBody(), comment.getIsArchive());
        int commentId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        comment.setCommentId(commentId);
    }

    @Override
    public Comment readCommentById(int commentId) {
        try {
            Comment comment = jdbcTemplate.queryForObject(SQL_SELECT_COMMENT, new CommentMapper(jdbcTemplate), commentId);

            return comment;

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Comment> getCommentsByPostId(String postId) {
        return jdbcTemplate.query(SQL_SELECT_COMMENT_BY_POST_ID, new CommentMapper(jdbcTemplate), postId);
    }

    @Override
    public void updateComment(Comment comment) {
        jdbcTemplate.update(SQL_UPDATE_COMMENT, comment.getPostId(), comment.getUserId(), comment.getCommentDate().toString(), comment.getCommentBody(), comment.getIsArchive(), comment.getCommentId());
    }

    @Override
    public void deleteCommentById(int commentId) {
        jdbcTemplate.update(SQL_DELETE_COMMENT, commentId);
    }

    @Override
    public List<Comment> getAllComments(int selectNum) {
        return jdbcTemplate.query(SQL_SELECT_ALL_COMMENTS, new CommentMapper(jdbcTemplate), selectNum);
    }

    private static final class CommentMapper implements RowMapper<Comment> {

        private JdbcTemplate jdbcTemplate;

        public CommentMapper(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        @Override
        public Comment mapRow(ResultSet rs, int i) throws SQLException {
            Comment comment = new Comment();
            comment.setCommentId(rs.getInt("CommentId"));
            comment.setPostId(rs.getInt("PostId"));
            comment.setUserId(rs.getInt("UserId"));
            comment.setCommentDate(rs.getTimestamp("CommentDate").toLocalDateTime());
            comment.setCommentBody(rs.getString("CommentBody"));
            comment.setIsArchive(rs.getBoolean("isArchive"));
            try {

                User user = jdbcTemplate.queryForObject(SQL_SELECT_USER, new UserMapper(), rs.getInt("UserId"));
                user.setAuthorities(new ArrayList<>(jdbcTemplate.query(SQL_SELECT_AUTHORITIES, new AuthorityMapper(), user.getUserName())));
                comment.setUser(user);
                Post post = jdbcTemplate.queryForObject(SQL_SELECT_POST, new PostMapper(jdbcTemplate), rs.getInt("PostId"));
                comment.setPost(post);
            } catch (SQLException | DataAccessException e) {
                return null;
            }
            return comment;
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
