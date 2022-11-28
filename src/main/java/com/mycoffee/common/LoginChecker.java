package com.mycoffee.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mycoffee.domain.DriverVO;
import com.mycoffee.domain.UserVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class LoginChecker {
	public final static String SN_LOGIN_USER = "SN_LOGIN_USER";
	public final static String SN_LOGIN_DRIVER = "SN_LOGIN_DRIVER";

	public static boolean isStoreManager(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVO user = (UserVO)session.getAttribute(SN_LOGIN_USER);
		if (user == null) {
			return false;
		} else if (user.getAuth() != 1) {
			log.info("user: " + user);
			return false;
		}
		return true;
	}

	public static boolean isCustomer(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVO user = (UserVO)session.getAttribute(SN_LOGIN_USER);
//		log.info("user: " + user);
		if (user == null) {
			return false;
		}
		return true;
	}

	public static boolean isDriverManager(HttpServletRequest request) {
		HttpSession session = request.getSession();
		DriverVO driver = (DriverVO)session.getAttribute(SN_LOGIN_DRIVER);
		if (driver == null) {
			return false;
		} else if (driver.getAuth() != 1) {
			log.info("driver: " + driver);
			return false;
		}
		return true;
	}

	public static boolean isDriver(HttpServletRequest request) {
		HttpSession session = request.getSession();
		DriverVO driver = (DriverVO)session.getAttribute(SN_LOGIN_DRIVER);
//		log.info("driver: " + driver);
		if (driver == null) {
			return false;
		}
		return true;
	}
}
