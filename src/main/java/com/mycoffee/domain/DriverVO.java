package com.mycoffee.domain;

import java.util.Date;

import lombok.Data;

@Data
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

//	
//	public String getDid() {
//		return did;
//	}
//
//	public void setDid(String did) {
//		this.did = did;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public int getAuth() {
//		return auth;
//	}
//	
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public void setAuth(int auth) {
//		this.auth = auth;
//	}
//
//	public String getMobile() {
//		return mobile;
//	}
//
//	public void setMobile(String mobile) {
//		this.mobile = mobile;
//	}
//
//	public int getOnwork() {
//		return onwork;
//	}
//
//	public void setOnwork(int onwork) {
//		this.onwork = onwork;
//	}
//
//	public int getPermitted() {
//		return permitted;
//	}
//
//	public void setPermitted(int permitted) {
//		this.permitted = permitted;
//	}
//
//	public String getReason() {
//		return reason;
//	}
//
//	public void setReason(String reason) {
//		this.reason = reason;
//	}
//
//	public int getRegistdate() {
//		return registdate;
//	}
//
//	public void setRegistdate(int registdate) {
//		this.registdate = registdate;
//	}
//
//	public int getUpdatedate() {
//		return updatedate;
//	}
//
//	public void setUpdatedate(int updatedate) {
//		this.updatedate = updatedate;
//	}
//
//	@Override
//	public String toString() {
//		return "DriverVO [did=" + did + ", password=" + password + ", name=" + name + ", auth=" + auth + ", mobile=" + mobile + ", onwork="
//				+ onwork + ", permitted=" + permitted + ", reason=" + reason + ", registdate=" + registdate
//				+ ", updatedate=" + updatedate + "]";
//	}

}
