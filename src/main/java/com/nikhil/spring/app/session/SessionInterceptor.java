package com.nikhil.spring.app.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SessionInterceptor implements HandlerInterceptor {

	static Logger LOGGER = LoggerFactory.getLogger(SessionInterceptor.class);
	
	@Autowired
	ObjectFactory<SessionContext> sessionContextFactory;

	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		LOGGER.debug("---Request Completed---");
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		LOGGER.debug("---method executed---");
	}

	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.debug("---Before Method Execution---");
		SessionContext sessionContext = sessionContextFactory.getObject();
		if (sessionContext == null || sessionContext.isAuthenticated == false) {
			res.getWriter().write("Not Authenticated");
			return false;
		}

		return true;
	}

}
