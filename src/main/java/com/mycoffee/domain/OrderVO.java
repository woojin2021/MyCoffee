package com.mycoffee.domain;


import java.util.Date;

import lombok.Data;

@Data
public class OrderVO {
	private String oid;//주문번호
	private String userid;//손님
	private String did;//배달원
	private int totalprice;//총 가격
	private int status;//code
	private Date registdate;
	private Date updatedate;
	private Date orderdate;
}
