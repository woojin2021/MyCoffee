package com.mycoffee.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j;

@Log4j
public class LoginChecker {
	public final static String SN_AUTH = "authorization";

	public static boolean isAdministrator(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer auth = (Integer)session.getAttribute(SN_AUTH);
		log.info("auth: " + auth);
		if (auth == null || auth != 1) {
			return false;
		}
		return true;
	}
}
