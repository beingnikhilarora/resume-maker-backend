package com.nikhil.spring.app.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;

import com.nikhil.spring.app.model.Response;
import com.nikhil.spring.app.model.User;
import com.nikhil.spring.app.service.UserService;
import com.nikhil.spring.app.session.SessionContext;

@Controller
@CrossOrigin(origins="*")
public class LoginController {

	@Autowired
	UserService userService;
	
	@Autowired
	ObjectFactory<SessionContext> sessionContextFactory;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public User register(@RequestBody User user) {
		userService.saveUser(user);
		return user;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Response login(@RequestBody User param,HttpServletResponse res) throws IOException {
		User user = userService.authenticate(param);
		if(user==null) {
			//res.setStatus(res.SC_NOT_FOUND);
			res.sendError(res.SC_NOT_FOUND,"Not found");
			return new Response("Error", "Check email or password.", null);
		}
		SessionContext sessionContext = sessionContextFactory.getObject();
		sessionContext.isAuthenticated = true;
		sessionContext.setUser(user);
		
		return new Response("OK", "", user);
	}
}
