package com.mycoffee.domain;

import lombok.Data;

/**
 * OrderOfTodyVO를 oid단위로 집계
 * @author wj.jeong
 */
@Data
public class OrderrInfo {

	private String oid;
	private String ordertime; // hh:mm:ss
	private int status;
	private String statusdisp;
	private String orderdetail;
	private int subtotalprice;
	private int totalprice;
	private String userinfo;

}
