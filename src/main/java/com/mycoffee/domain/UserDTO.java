package com.mycoffee.domain;

import java.util.Date;

import lombok.Data;

@Data
public class UserDTO {
	private String userid;
	private String password;
	private String name;
	private int auth;
	private String address;
	private String mobile;
	private Date registdate;
	private Date updatedate;
}
