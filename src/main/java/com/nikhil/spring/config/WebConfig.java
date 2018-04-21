package com.nikhil.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.nikhil.spring.app.session.SessionInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"com.nikhil.spring"})
public class WebConfig extends WebMvcConfigurerAdapter {
	public WebConfig() {
		System.out.println("\n\n\n***Web Config created...***\n\n\n");
	}
	
	@Bean
	public SessionInterceptor sessionInterceptor() {
	    return new SessionInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(sessionInterceptor()).excludePathPatterns("/login").excludePathPatterns("/register");
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
	  registry.addMapping("/**")
	   	  .allowedOrigins("*")
		  .allowedMethods("POST", "GET",  "PUT", "OPTIONS", "DELETE");
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/resources/**")
          .addResourceLocations("/resources/"); 
    }
}
