package com.nikhil.spring.app.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nikhil.spring.app.model.Response;
import com.nikhil.spring.app.model.User;
import com.nikhil.spring.app.service.UserService;
import com.nikhil.spring.app.session.SessionContext;

@Controller
public class LoginController {

	static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	UserService userService;

	@Autowired
	ObjectFactory<SessionContext> sessionContextFactory;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public User register(@RequestBody User user, HttpServletRequest req) {
		LOGGER.debug("Inside Register");
		LOGGER.debug("URL : " + req.getRequestURI());
		userService.saveUser(user);
		return user;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Response login(@RequestBody User param, HttpServletRequest req, HttpServletResponse res) throws IOException {

		LOGGER.info("Inside Login");
		LOGGER.info("URL : " + req.getRequestURI());

		SessionContext sessionContext = sessionContextFactory.getObject();
		
		if(sessionContext.isAuthenticated == true) {
			return new Response(Response.ERROR, "Already Logged In", null);
		}
		
		User user = userService.authenticate(param);
		if (user == null) {
			// res.setStatus(res.SC_NOT_FOUND);
			LOGGER.error("User Not Found. Sending Error Response");
			//res.sendError(HttpServletResponse.SC_NOT_FOUND, "Not found");
			return new Response(Response.ERROR, "Check email or password.", null);
		}
		
		sessionContext.isAuthenticated = true;
		sessionContext.setUser(user);

		return new Response(Response.SUCCESS, "", user);
	}
	
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	@ResponseBody
	public Response Logout(HttpServletRequest req) {
		LOGGER.debug("Inside Logout");
		LOGGER.info("URL : " + req.getRequestURI());
		
		SessionContext sessionContext = sessionContextFactory.getObject();
		if(sessionContext.isAuthenticated == false) {
			return new Response(Response.ERROR,"Not Logged In",null);
		}
		else {
			sessionContext.isAuthenticated = false;
			sessionContext.setUser(null);
		}
		
		return new Response(Response.SUCCESS,"Logged Out Successfully",null);
	}
}
