/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.service;

import com.sg.fidgetblog.dao.UserDao;
import com.sg.fidgetblog.dto.User;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jono
 */
public class UserServiceImpl implements UserService {

    UserDao userDao;

    @Inject
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Override
    public User readUserById(int userId) {
        return userDao.readUserById(userId);
    }

    @Override
    public User readUserByUsername(String userName) {
        return userDao.readUserByUserName(userName);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(String userName) {
        userDao.deleteUserByUserName(userName);
    }

    @Override
    public List<User> readAllUsers(int numChoice) {
        return userDao.readAllUsers(numChoice);
    }

    @Override
    public void deleteUserAuthority(String userName, String authority) {
        userDao.deleteUserAuthority(userName, authority);
    }

    @Override
    public void deleteAllAuthoritiesByUserName(String userName) {
        userDao.deleteAllAuthoritiesByUserName(userName);
    }

    @Override
    public List<User> actuallyReadAllUsers() {
        return userDao.actuallyReadAllUsers();
    }

}
