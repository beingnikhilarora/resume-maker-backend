package com.nikhil.spring.app.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nikhil.spring.app.utils.TemplateGeneratorUtil;

@Controller
public class ResumeController {

	@RequestMapping(value="/generate",method=RequestMethod.GET)
	@ResponseBody
	public void generateTemplate() throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "nikhil arora");
		TemplateGeneratorUtil.generate("/Users/nikhilarora/Documents/shared/hello.html", "templates/template1/index.vm", map);
	}
}
