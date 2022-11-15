package com.mycoffee.domain;

import lombok.Data;

@Data
public class DriverInfo {

	private String oid;
	private String did;
	private String address;
	private int pieces;
	private String ordertime; // hh:mm:ss
	private int status;
	private String statusdisp;
	private String orderdetail;
	private int subtotalprice;
	private int totalprice;
	private String userinfo;
	private int permitted;
	
	public String getStatus2() {
		if (status == 3) {
			return "배달 대기";
		} else if (status == 4) {
			return "배달 중";
		} else {
			return "배달 완료";
		}
	}
}
