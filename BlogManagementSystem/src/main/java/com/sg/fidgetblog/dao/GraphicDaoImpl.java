/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dao;

import static com.sg.fidgetblog.dao.PostDaoImpl.formatter;
import com.sg.fidgetblog.dto.Graphic;
import com.sg.fidgetblog.dto.Post;
import com.sg.fidgetblog.dto.User;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.inject.Inject;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jono
 */
public class GraphicDaoImpl implements GraphicDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public GraphicDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_GRAPHIC
            = "insert into Image (Image) values (?)";
    private static final String SQL_UPDATE_GRAPHIC_POSTID
            = "update Image set PostId = ? where ImageId = ?";

    private static final String SQL_DELETE_GRAPHIC
            = "update Image set isArchived = 1 where ImageId = ?";

    private static final String SQL_SELECT_GRAPHIC
            = "select * from Image where ImageId = ?";
    private static final String SQL_SELECT_ALL_GRAPHICS
            = "select * from Image";
    private static final String SQL_SELECT_USER = "select * from User where UserID = ?";
    private static final String SQL_SELECT_POST
            = "select * from Post where PostId = ?";
    private static final String SQL_SELECT_AUTHORITIES = "select * from Authorities where UserName = ?";

    @Override
    public int createGraphic(MultipartFile graphic) {
        try {
            byte[] byteArr = graphic.getBytes();
            InputStream input = new ByteArrayInputStream(byteArr);
            jdbcTemplate.update(SQL_INSERT_GRAPHIC, input);
        } catch (IOException e) {

        }
        return jdbcTemplate.queryForObject("select last_insert_id()", int.class);
    }

    @Override
    public Graphic readGraphicById(int graphicId) {
        try {
            Graphic graphic = jdbcTemplate.queryForObject(SQL_SELECT_GRAPHIC, new GraphicMapper(jdbcTemplate), graphicId);

            return graphic;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void deleteGraphicById(int graphicId) {
        jdbcTemplate.update(SQL_DELETE_GRAPHIC, graphicId);
    }

    @Override
    public void updateGraphicPostId(Graphic graphic) {
        jdbcTemplate.update(SQL_UPDATE_GRAPHIC_POSTID, graphic.getPost().getPostId(), graphic.getImageId());
    }

    private static final class GraphicMapper implements RowMapper<Graphic> {

        private JdbcTemplate jdbcTemplate;

        public GraphicMapper(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        @Override
        public Graphic mapRow(ResultSet rs, int i) throws SQLException {
            Graphic graphic = new Graphic();
            graphic.setImage(rs.getBytes("Image"));
            int postId = rs.getInt("PostId");
            int userId = rs.getInt("UserId");
            graphic.setImageAltText(rs.getString("ImageAltText"));
            graphic.setIsArchive(rs.getBoolean("IsArchived"));
            try {
                User user = jdbcTemplate.queryForObject(SQL_SELECT_USER, new UserMapper(), userId);
                Post post = jdbcTemplate.queryForObject(SQL_SELECT_POST, new PostMapper(jdbcTemplate), postId);
            } catch (Exception e) {
            }
            return graphic;
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

    private static final class AuthorityMapper implements RowMapper<String> {

        @Override
        public String mapRow(ResultSet rs, int i) throws SQLException {
            return rs.getString("Authority");
        }

    }

}
