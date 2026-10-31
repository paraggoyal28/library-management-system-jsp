package com.lms.service;

import com.lms.pojo.User;

public interface UserService {
	public User checkLogin(String username, String password);

}
