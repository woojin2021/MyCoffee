package com.mycoffee.domain;

import java.util.Date;

import lombok.Getter;

/**
 * tbl_order, tbl_code, tbl_user 통합데이터
 * 
 * @author wj.jeong
 */
@Getter
public class OrderOfTodyVO {
	
	private String oid;
	private Date orderdate;
	private int status;
	private String statusdisp;
	private int ptype;
	private String temperaturedisp;
	private String pname;
	private int pieces;
	private int price;
	private int totalprice;
	private String name;
	private String mobile;

}
