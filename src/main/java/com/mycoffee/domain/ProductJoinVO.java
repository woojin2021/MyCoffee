package com.mycoffee.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ProductJoinVO {
	private String pid;
	private String pcategory;
	private String pname;
	private int ptype;
	private String description;
	private int calorie;
	private int fat;
	private int sugars;
	private int sodium;
	private int caffeine;
	private String imagefile;
	private Date registdate;
	private Date updatedate;
	private int temperature;
	private int price;
	private int onsale;
	private int capacity;
}
