package com.nikhil.spring.app.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nikhil.spring.app.model.User;

@Repository
public class UserDaoImpl extends AbstractDao implements UserDao {

	public void saveUser(User user) {
		// TODO Auto-generated method stub
		persist(user);
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		update(user);
	}

	public User findById(int id) {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("id",id));
        return (User) criteria.uniqueResult();
	}

	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("email",email));
        return (User) criteria.uniqueResult();
	}
}
