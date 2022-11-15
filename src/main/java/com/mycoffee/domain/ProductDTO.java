package com.mycoffee.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ProductDTO {
	  private String pid;
	  private String pcategory;
	  private int temperature;
	  private int capacity;
	  private int price;
	  private int onsale;
	  private Date registdate;
	  private Date updatedate;
}
