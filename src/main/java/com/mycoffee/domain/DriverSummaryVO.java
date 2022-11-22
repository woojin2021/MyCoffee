package com.mycoffee.domain;

import lombok.Data;

@Data
public class DriverSummaryVO {
	
	private String did;
	private String name;
	private String address;
	private int reserved;
	private int completed;
	private int total;
	private int permitted;
	
	public int getStatus() {
		if (permitted == 0) {
			return 1;
		} else if (permitted == 2) {
			return 2;
		} else {
			if (address == null) {
				return 3;
			} else {
				return 4;
			}
		}
	}
	
	public String getStatusDisp() {
		switch (getStatus()) {
		case 1:
			return "승인대기";
		case 2:
			return "승인거부";
		case 3:
			return "대기중";
		case 4:
			return "배달중";
		default:
			return "";
		}
	}
}
