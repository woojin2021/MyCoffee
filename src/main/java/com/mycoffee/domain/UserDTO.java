package com.mycoffee.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
	private String userid;
	private String password;
	private int auth;
	private String address;
	private String mobile;
	private Date registdate;
	private Date updatedate;
}
