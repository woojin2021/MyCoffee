package com.mycoffee.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

@ControllerAdvice
@Log4j
public class CommonExceptionHandler {
	
//    @ResponseStatus(HttpStatus.NOT_FOUND) <- for JSON
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		String dispatcher = "redirect: /menu/User_Drink_Menu";
		
		if (requestURI.matches("/admin(.*)" )) {
			dispatcher = "/admin/login";
		} else if (requestURI.matches("/driver(.*)" )) {
			dispatcher = "/driver/login";
		}
		
		log.info(requestURI + " -> " + dispatcher);
        return dispatcher;
    }

}
