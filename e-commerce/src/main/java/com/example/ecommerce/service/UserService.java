package com.example.ecommerce.service;

import com.example.ecommerce.entity.User;

public interface UserService {

    void saveUser(User user);

    User findUserByEmail(String email);


}
