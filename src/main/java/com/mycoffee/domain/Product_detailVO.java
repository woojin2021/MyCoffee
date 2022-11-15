package com.mycoffee.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Product_detailVO {
	private String pid;
	private String pcategory;
	private Date registdate;
	private Date updatedate;
	private int temperature;
	private int price;
	private int onsale;
	private int capacity;
}
