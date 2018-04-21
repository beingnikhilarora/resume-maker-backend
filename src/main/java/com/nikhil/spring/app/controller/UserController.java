package com.nikhil.spring.app.controller;

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
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	ObjectFactory<SessionContext> sessionContextFactory;
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ResponseBody
	public Response update(@RequestBody User user) {
		userService.updateUser(user);
		return new Response(Response.SUCCESS, "", user);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public Response get() {
		SessionContext sessionContext = sessionContextFactory.getObject();
		User user = sessionContext.getUser();
		return new Response(Response.SUCCESS, "", user);
	}
}
