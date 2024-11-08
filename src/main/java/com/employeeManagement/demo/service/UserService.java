package com.employeeManagement.demo.service;

import com.employeeManagement.demo.entity.User;
import com.employeeManagement.demo.user.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

	void save(WebUser webUser);

}
