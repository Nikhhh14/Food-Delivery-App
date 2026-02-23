package com.food.dao;

import com.food.model.User;

public interface UserDAO {

    int insert(User user);

    User fetchByEmail(String email);
}
