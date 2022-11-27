package com.mycoffee.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 장바구니/주문이력용 데이터 포멧
 * 
 * @author wj.jeong
 */
@Data
public class OrderData {
	private String oid;
	private String userid;
	private int totalprice;
	private int status;
	private String statusDisp;
	private Date registdate;
	private Date orderdate;
	private List<OrderDetailData> details;

	@Data
	public class OrderDetailData {
		private String pid;
		private String pcategory;
		private String pname;
		private int temperature;
		private String temperatureDisp;
		private int capacity;
		private String capacityDisp;
		private int pieces;
		private int price;
	}

	public OrderDetailData newDetail() {
		return new OrderDetailData();
	}

}
