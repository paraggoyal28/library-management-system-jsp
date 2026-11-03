package com.lms.dao;

import java.util.List;

import com.lms.pojo.User;

public interface UserDao {

	public User checkLogin(String username, String password);

	public boolean addUser(User user);
	
	public List<User> getAllUserList();

}
