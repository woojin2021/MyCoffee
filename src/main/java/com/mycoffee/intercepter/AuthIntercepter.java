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
		
		if (auth.role() == Auth.Role.STORE_MANAGER) {
			if (LoginChecker.isAdministrator(request) == false) {
				log.info("RequestURI: " + request.getRequestURI());
				if (auth.type() == DispatcherType.ASYNC) {
					// ajax로 접근하는 경우에는 406 error를 반환
					((HttpServletResponse)response).sendError(HttpStatus.NOT_ACCEPTABLE.value());
				} else {
					// 로그인 화면으로 이동(default)
					((HttpServletResponse)response).sendRedirect("/admin/login");
				}
				return false;
			}
		}
		
		return true;
	}

}
