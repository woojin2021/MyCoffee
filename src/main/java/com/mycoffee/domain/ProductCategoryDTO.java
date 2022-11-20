package com.mycoffee.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ProductCategoryDTO {
	private String pcategory;
	private String pname;
	private int ptype;
	private String description;
	private int calorie;
	private int caffeine;
	private int sugars;
	private int protein;
	private int fat;
	private int sodium;
	private String imagefile;
	private Date registdate;
	private Date updatedate;

}
