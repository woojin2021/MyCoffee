package com.mycoffee.domain;

import java.util.Date;

import lombok.Getter;

@Getter
public class ProductCategoryVO {
	private String pcategory;
	private String pname;
	private int ptype;
	private String description;
	private int calorie;
	private int fat;
	private int sugars;
	private int sodium;
	private int caffeine;
	private int protein;
	private String imagefile;
	private Date registdate;
	private Date updatedate;
}
