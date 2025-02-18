package com.example.ecommerce.DAO;


import com.example.ecommerce.entity.User;

public interface UserDAO  {

    void saveUser(User user);

    User findUserByEmail(String email);


}
