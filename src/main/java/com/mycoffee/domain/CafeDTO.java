package com.mycoffee.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CafeDTO {

	private int cafeid;
	private String cafename;
	private int cafeopen;
	private Date registdate;
	private Date updatedate;

}
