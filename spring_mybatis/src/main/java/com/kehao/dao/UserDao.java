package com.kehao.dao;

import com.kehao.pojo.User;

public interface UserDao {

    void updateUser(User user);

    User selectUserByName(String name);
}
