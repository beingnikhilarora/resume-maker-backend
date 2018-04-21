package com.nikhil.spring.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nikhil.spring.app.service.ResumeService;

@Controller
public class ResumeController {

	@Autowired
	private ResumeService resumeService;
	
	@RequestMapping(value="/generate",method=RequestMethod.GET)
	@ResponseBody
	public void generateTemplate() throws IOException {
		resumeService.generateTemplate();
	}
}
