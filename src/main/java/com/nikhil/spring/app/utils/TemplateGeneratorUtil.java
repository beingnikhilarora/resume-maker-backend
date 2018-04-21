package com.nikhil.spring.app.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class TemplateGeneratorUtil {

	public static void generate(String savePath,String templatePath,Map<String, String> content) throws IOException {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
		
		Template template = ve.getTemplate(templatePath);
		VelocityContext vc = new VelocityContext();
		vc.put("user", content);
		
		StringWriter writer = new StringWriter();
		template.merge(vc, writer);
		
		System.out.println(writer);
		
		BufferedWriter fileWriter = new BufferedWriter(new FileWriter(savePath));
		fileWriter.write(writer.toString());
	     
		fileWriter.close();
	}
}
