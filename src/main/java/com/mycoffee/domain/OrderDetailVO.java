package com.mycoffee.domain;

import java.util.Date;

import lombok.Data;

@Data
public class OrderDetailVO {
	private String oid;
	private String pid;
	private int pieces;
	private int price;
	private Date registdate;
	private Date updatedate; 
}
