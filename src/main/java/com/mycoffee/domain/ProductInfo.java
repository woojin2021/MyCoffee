package com.mycoffee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductInfo {
	
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
	private String details;
	
}
