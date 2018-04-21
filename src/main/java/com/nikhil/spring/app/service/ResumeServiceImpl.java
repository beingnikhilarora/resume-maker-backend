package com.nikhil.spring.app.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikhil.spring.app.model.User;
import com.nikhil.spring.app.session.SessionContext;
import com.nikhil.spring.app.utils.TemplateGeneratorUtil;

@Service
public class ResumeServiceImpl implements ResumeService {
	
	@Autowired
	ObjectFactory<SessionContext> sessionContextFactory;

	public void generateTemplate() {
		Map<String, String> map = new HashMap<String, String>();
		SessionContext sessionContext = sessionContextFactory.getObject();
		User user = sessionContext.getUser();
		
		map.put("name", user.getName());
		map.put("email", user.getEmail());
		map.put("employer", user.getEmployer());
		map.put("project", user.getProject());
		
		try {
			TemplateGeneratorUtil.generate("/Users/nikhilarora/Documents/shared/hello.html", "templates/template1/index.vm", map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
