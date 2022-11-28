package com.mycoffee.intercepter;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mycoffee.common.Auth;
import com.mycoffee.common.LoginChecker;

import lombok.extern.log4j.Log4j;

/**
 * 인증/권환 확인
 * @author wj.jeong
 *
 */
@Component
@Log4j
public class AuthIntercepter implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		
		HandlerMethod method = (HandlerMethod) handler;
		Auth auth = method.getMethodAnnotation(Auth.class);
		if(auth == null) {
			return true;
		}
		
		boolean isValid = true;
		String redirectURI = "";
		if (auth.role() == Auth.Role.STORE_MANAGER) {
			if (LoginChecker.isStoreManager(request) == false) {
				isValid = false;
				redirectURI = "/admin/login";
			}
		} else if (auth.role() == Auth.Role.CUSTOMER) {
			if (LoginChecker.isCustomer(request) == false) {
				isValid = false;
				redirectURI = "/user/User_Login";
			}
		} else if (auth.role() == Auth.Role.DRIVER) {
			if (LoginChecker.isDriver(request) == false) {
				isValid = false;
				redirectURI = "/driver/login";
			}
		} else if (auth.role() == Auth.Role.DRIVER_MANAGER) {
			if (LoginChecker.isDriverManager(request) == false) {
				isValid = false;
				redirectURI = "/driver/login";
			}
		}
		
		if (isValid == false) {
			log.info("RequestURI: " + request.getRequestURI() + "[" + auth.role() + " is necessary]");
			if (auth.type() == DispatcherType.ASYNC) {
				// ajax로 접근하는 경우에는 406 error를 반환
				((HttpServletResponse)response).sendError(HttpStatus.NOT_ACCEPTABLE.value());
			} else {
				// 로그인 화면으로 이동(default)
				((HttpServletResponse)response).sendRedirect(redirectURI);
			}
			return false;
		}
		
		return true;
	}

}
