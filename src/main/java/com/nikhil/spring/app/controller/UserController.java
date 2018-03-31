package com.nikhil.spring.app.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins="*")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	ObjectFactory<SessionContext> sessionContextFactory;
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public Response update(@RequestBody User user) {

		User usr = userService.getUser(user.getId());
		
		if(usr==null)
			return new Response("ERROR", "User not found", null);
		if(user.getEmail()!=null && user.getEmail()!="")
			usr.setEmail(user.getEmail());
		if(user.getName()!=null && user.getName()!="")
			usr.setName(user.getName());
		if (user.getPassword() != null && user.getPassword() != "")
			usr.setPassword(DigestUtils.md5Hex(user.getPassword()));
		
		userService.updateUser(usr);
		return new Response("OK", "", usr);
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public Response get() {
		SessionContext sessionContext = sessionContextFactory.getObject();
		User user = sessionContext.getUser();
		
		return new Response("OK", "", user);
	}
}
