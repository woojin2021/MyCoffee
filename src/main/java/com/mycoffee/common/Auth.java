package com.mycoffee.common;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.servlet.DispatcherType;

/**
 * Intercepter에서 인증처리를 위한 체크항목
 * @author wj.jeong
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface Auth {

	/**
	 * 처리에 필요한 인증
	 * @return
	 */
	Role role() default Role.NONE;

	DispatcherType type() default DispatcherType.REQUEST;
	
	enum Role {
		/**
		 * 인증불요
		 */
		NONE,
		/**
		 * 고객
		 */
		CUSTOMER,
		/**
		 * 배달원
		 */
		DRIVER,
		/**
		 * 점포 관리자
		 */
		STORE_MANAGER,
		/**
		 * 배달원 관리자
		 */
		DRIVER_MANAGER,
		/**
		 * 통합 관리자
		 */
		ADMIN
	}
}
