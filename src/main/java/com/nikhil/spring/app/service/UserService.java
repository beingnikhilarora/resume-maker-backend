package com.nikhil.spring.app.service;

import com.nikhil.spring.app.model.User;

public interface UserService {

	void saveUser(User user);
	
	void updateUser(User user);
	
	User getUser(int id);
	
	User authenticate(User user);
	
	User getUser(String email);
}
