package com.mycoffee.service;

import java.util.List;

import com.mycoffee.domain.DriverDTO;
import com.mycoffee.domain.DriverSummaryVO;

public interface DriverManager {

	public List<DriverSummaryVO> getDriverSummaryList();

	/* 등록 승인 */
	public void approveRegist(DriverDTO driver);

	/* 등록 거부 */
	public void rejectRegist(DriverDTO driver);

}
