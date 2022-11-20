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
	
	
	public String getStatus() {
		if (permitted == 0) {
			return "승인대기";
		} else if (permitted == 2) {
			return "승인거부";
		} else {
			if (address == null) {
				return "대기중";
			} else {
				return "배달중";
			}
		}
	}
}
