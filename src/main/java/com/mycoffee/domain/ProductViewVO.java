package com.mycoffee.domain;

import lombok.Getter;

@Getter
public class ProductViewVO {
	  private String pid;
	  private String pcategory;
	  private int temperature;
	  private String temperatureDisp;
	  private int capacity;
	  private String capacityDisp;
	  private int price;
	  private int onsale;
}
