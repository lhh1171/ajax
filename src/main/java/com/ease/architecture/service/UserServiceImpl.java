package com.ease.architecture.service;

import com.ease.architecture.dao.UserDao;
import com.ease.architecture.entity.User;
import com.ease.architecture.utils.RedisUtil;

import java.util.UUID;

public class UserServiceImpl {

    private UserDao userDao = new UserDao();

    public User findUserById(String id) {
        return userDao.findByUserId(id);
    }

    public User findUserByName(String username) {
        Object o = RedisUtil.getInstance().getConnection().getBucket(username).get();
        if (o instanceof User) {
            return (User) o;
        }
        User user = userDao.findByName(username);
        if (user != null) {
            RedisUtil.getInstance().getConnection().getBucket(user.getName()).set(user);
        }
        return user;
    }

    public User findUserByNameAndPassword(String username, String password) {
        return userDao.findByNameAndPassword(username, password);
    }

//    public User login(String username, String password) {
//        return findUserByNameAndPassword(username, password);
//    }

    public boolean register(User user) {
        if (findUserByName(user.getName()) != null) {
            return true;
        }
        if (user.getId() == null || user.getId().equals("")) {
            user.setId(UUID.randomUUID().toString());
        }
        boolean result = userDao.insertUser(user) != 0;
        if (result) {
            RedisUtil.getInstance().getConnection().getBucket(user.getName()).set(user);
        }
        return result;
    }
}
