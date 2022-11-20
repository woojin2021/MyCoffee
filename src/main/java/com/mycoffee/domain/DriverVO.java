package com.mycoffee.domain;

import java.util.Date;

import lombok.Getter;

@Getter
public class DriverVO {

//	1. 배달원 이메일(아이디)
	private String did;
	
//	2. 배달원 비밀번호
	private String password;
	
//	3. 배달원 이름
	private String name;
	
//	4. 배달원 구분
	private int auth;
	
//	5. 휴대폰
	private String mobile;
	
//	6. 근무중
	private int onwork;
	
//	7. 등록승인
	private int permitted;
	
//	8. 승인거부이유
	private String reason;
	
//	9. 등록날짜
	private Date registdate;
	
//	10. 수정날짜
	private Date updatedate;

}
