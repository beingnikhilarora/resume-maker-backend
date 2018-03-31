package com.nikhil.spring.app.dao;

import com.nikhil.spring.app.model.User;

public interface UserDao {

	public void saveUser(User user);
	
	public void updateUser(User user);
	
	public User findById(int id);
	
	public User findByEmail(String email);
}
