/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dao;

import com.sg.fidgetblog.dto.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jono
 */
public class UserDaoImpl implements UserDao {

    /////INSERT
    private static final String SQL_INSERT_USER = "insert into User (UserName, password, IsActive) value(?, ?, ?)";
    private static final String SQL_INSERT_AUTHORITY
            = "insert into Authorities (username, authority) values (?, ?)";

    /////SELECT
    private static final String SQL_SELECT_USER = "select * from User where UserID = ?";
    private static final String SQL_SELECT_USER_BY_USERNAME = "select * from User where UserName = ?";
    private static final String SQL_SELECT_AUTHORITIES = "select * from Authorities where UserName = ?";

    /////SELECT ALL
    private static final String SQL_SELECT_ALL_USERS = "select * from User order by User.UserId desc limit ?,10;";
    private static final String SQL_ACTUALLY_SELECT_ALL_USERS = "select * from User";

    /////UPDATE
    private static final String SQL_UPDATE_USER = "update User set UserName = ?, password = ?, IsActive = ?, isBanned = ? where UserID = ?";

    /////DELETE
    private static final String SQL_DELETE_USER = "update User set isActive = 0 where UserName = ?";
    private static final String SQL_DELETE_AUTHORITY = "delete from Authorities where username = ? and Authority = ?";
    private static final String SQL_DELETE_ALL_AUTHORITIES_BY_USERNAME = "delete from Authorities where username = ?";

    JdbcTemplate jdbcTemplate;

    @Inject
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void createUser(User user) {
        jdbcTemplate.update(SQL_INSERT_USER, user.getUserName(), user.getPassWord(), user.getIsActive());
        int userId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        user.setUserId(userId);

        //insert user's roles
        ArrayList<String> authorities = user.getAuthorities();
        for (String authority : authorities) {
            jdbcTemplate.update(SQL_INSERT_AUTHORITY, user.getUserName(), authority);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void createAuthority(User user, String authority) {
        jdbcTemplate.update(SQL_INSERT_AUTHORITY, user.getUserName(), authority);
    }

    @Override
    public User readUserById(int userId) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_USER, new UserMapper(), userId);
            user.setAuthorities(new ArrayList<>(jdbcTemplate.query(SQL_SELECT_AUTHORITIES, new AuthorityMapper(), user.getUserName())));
            return user;

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public User readUserByUserName(String username) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_USERNAME, new UserMapper(), username);
            user.setAuthorities(new ArrayList<>(jdbcTemplate.query(SQL_SELECT_AUTHORITIES, new AuthorityMapper(), user.getUserName())));
            return user;

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void updateUser(User user) {
        User old = readUserById(user.getUserId());
        jdbcTemplate.update(SQL_DELETE_ALL_AUTHORITIES_BY_USERNAME, old.getUserName());
        jdbcTemplate.update(SQL_UPDATE_USER, user.getUserName(), user.getPassWord(), user.getIsActive(), user.getIsBanned(), user.getUserId());
        ArrayList<String> authorities = user.getAuthorities();
        for (String authority : authorities) {
            jdbcTemplate.update(SQL_INSERT_AUTHORITY, user.getUserName(), authority);
        }
    }

    @Override
    public void deleteUserByUserName(String userName) {
        jdbcTemplate.update(SQL_DELETE_USER, userName);
    }

    @Override
    public void deleteAllAuthoritiesByUserName(String userName) {
        jdbcTemplate.update(SQL_DELETE_ALL_AUTHORITIES_BY_USERNAME, userName);
    }

    @Override
    public void deleteUserAuthority(String userName, String authority) {
        jdbcTemplate.update(SQL_DELETE_AUTHORITY, userName, authority);
    }

    @Override
    public List<User> readAllUsers(int numChoice) {
        List<User> users = jdbcTemplate.query(SQL_SELECT_ALL_USERS, new UserMapper(), numChoice);
        for (User user : users) {
            user.setAuthorities(new ArrayList<>(jdbcTemplate.query(SQL_SELECT_AUTHORITIES, new AuthorityMapper(), user.getUserName())));
        }
        return users;
    }

    @Override
    public List<User> actuallyReadAllUsers() {
        List<User> users = jdbcTemplate.query(SQL_ACTUALLY_SELECT_ALL_USERS, new UserMapper());
        for (User user : users) {
            user.setAuthorities(new ArrayList<>(jdbcTemplate.query(SQL_SELECT_AUTHORITIES, new AuthorityMapper(), user.getUserName())));
        }
        return users;
    }

    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {

            User user = new User();
            user.setUserId(rs.getInt("UserId"));
            user.setUserName(rs.getString("UserName"));
            user.setPassWord(rs.getString("password"));
            user.setIsActive(rs.getBoolean("IsActive"));
            user.setIsBanned(rs.getBoolean("isBanned"));

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
