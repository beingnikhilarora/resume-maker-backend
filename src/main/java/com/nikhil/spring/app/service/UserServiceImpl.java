package com.nikhil.spring.app.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nikhil.spring.app.dao.UserDao;
import com.nikhil.spring.app.model.User;
import com.nikhil.spring.app.utils.UserUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao dao;
	
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		UserUtil.encryptPassword(user);
		dao.saveUser(user);
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		dao.updateUser(user);
	}

	public User getUser(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	public User getUser(String email) {
		return dao.findByEmail(email);
	}
	public User authenticate(User user) {
		// TODO Auto-generated method stub
		//UserUtil.encryptPassword(user);
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		User user2 = getUser(user.getEmail());
		
		if(user2==null)
			return null;
		
		if(user2.getPassword().equals(user.getPassword()))
			return user2;
		
		return null;
		
	}

}
