package com.mycoffee.domain;

import java.util.Date;

import lombok.Getter;

@Getter
public class DriverOrderVO {

//	주문번호, 주문내역(음료 이름),수량, 주소, 
	
	private String oid;		//order테이블 oid 주문번호
	private String did;
	private String pname;	//product테이블 pname 상품이름
	private int pieces;		//order_detail테이블 pieces 수량
	private String address;	//user테이블 address 주소
	private Date orderdate;
	private int status;
	private String statusdisp;
	private int ptype;
	private String temperaturedisp;
	private int price;
	private int totalprice;
	private String name;
	private String mobile;
	
}
