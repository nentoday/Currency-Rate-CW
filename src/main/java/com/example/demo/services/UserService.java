package com.example.demo.services;

import com.example.demo.model.UserEntity;

public interface UserService {
    void saveUser(UserEntity user);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String nickname);
}
