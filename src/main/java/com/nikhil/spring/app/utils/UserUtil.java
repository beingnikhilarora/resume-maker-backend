package com.nikhil.spring.app.utils;

import org.apache.commons.codec.digest.DigestUtils;

import com.nikhil.spring.app.model.User;

public class UserUtil {

	public static void encryptPassword(User user) {
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
	}
}
