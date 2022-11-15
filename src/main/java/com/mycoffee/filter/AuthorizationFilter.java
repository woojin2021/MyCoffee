package com.mycoffee.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.core.config.Order;
import org.springframework.http.HttpStatus;

import com.mycoffee.common.LoginChecker;

import lombok.extern.log4j.Log4j;

@Order(1)
@Log4j
public class AuthorizationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String loginPage = "/admin/login";
		HttpServletRequest httpRequest = (HttpServletRequest)request; 
		String requestURI = httpRequest.getRequestURI();
		
		if (requestURI.matches("/admin/(.*)" ) && 
				requestURI.equals(loginPage) == false && 
				LoginChecker.isAdministrator(httpRequest) == false) {
			log.info("prevented access to " + requestURI);
			if (requestURI.matches("/admin/async/(.*)" ) || request.getDispatcherType() == DispatcherType.ASYNC) {
				((HttpServletResponse)response).sendError(HttpStatus.NOT_ACCEPTABLE.value());
			} else {
				((HttpServletResponse)response).sendRedirect(loginPage);
			}
			return;
		}
		chain.doFilter(request, response);
	}

}
