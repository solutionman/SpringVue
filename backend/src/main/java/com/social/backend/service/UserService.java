package com.social.backend.service;

import com.social.backend.model.User;

public interface UserService {
    User findByUsername(String username);
}
