package com.mycoffee.domain;

import java.util.Date;

import lombok.Getter;

@Getter
public class OrderWithDetailVO {
	private String oid;
	private String userid;
	private int totalprice;
	private int status;
	private String statusDisp;
	private Date registdate;
	private Date orderdate;
	private String pid;
	private String pcategory;
	private String pname;
	private int temperature;
	private String temperatureDisp;
	private int capacity;
	private String capacityDisp;
	private int pieces;
	private int price;
	private int onsale;

}
