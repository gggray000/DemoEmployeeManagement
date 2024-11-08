package com.employeeManagement.demo.dao;

import com.employeeManagement.demo.entity.User;

public interface UserDao {

    User findByUserName(String userName);

    void save(User theUser);
}
