package com.social.backend.service;

import com.social.backend.dao.UserDao;
import com.social.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsername(String login) {
        return userDao.findByUsername(login);
    }
}
