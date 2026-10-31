package com.lms.dao;

import com.lms.pojo.User;

public interface UserDao {

	public User checkLogin(String username, String password);
}
