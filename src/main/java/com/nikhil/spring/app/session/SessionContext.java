package com.nikhil.spring.app.session;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.nikhil.spring.app.model.User;

@Scope( value = "session" )
@Component
public class SessionContext {

	public boolean isAuthenticated;
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
