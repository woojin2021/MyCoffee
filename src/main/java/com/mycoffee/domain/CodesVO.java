package com.mycoffee.domain;

import java.util.Date;

import lombok.Getter;

@Getter
public class CodesVO {
	private int code;
	private String type;
	private String disp;
	private Date registdate;
	private Date updatedate;
}
