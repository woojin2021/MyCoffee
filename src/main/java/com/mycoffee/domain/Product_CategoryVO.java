package com.mycoffee.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Product_CategoryVO {
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
}
