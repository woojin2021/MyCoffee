package com.mycoffee.domain;

import lombok.Data;

@Data
public class DriverDTO {

	private String did;
	private String password;
	private String name;
	private int auth;
	private String mobile;
	private int onwork;
	private int permitted;
	private String reason;

}
