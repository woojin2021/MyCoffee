package com.mycoffee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycoffee.domain.DriverDTO;
import com.mycoffee.domain.DriverSummaryVO;
import com.mycoffee.mapper.DriverMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@AllArgsConstructor
@Log4j
@Service
public class DriverManagerImpl implements DriverManager {

	private DriverMapper drivermapper;
	
	@Override
	public List<DriverSummaryVO> getDriverSummaryList() {
		log.info("getList..............");
		return drivermapper.selectDriverSummaryList();
	}

	@Override
	public void approveRegist(DriverDTO driver) {
		driver.setPermitted(1);  // 1:등록 승인
		drivermapper.updatePermition(driver);
	}

	@Override
	public void rejectRegist(DriverDTO driver) {
		driver.setPermitted(2);  // 2:승인 거부
		drivermapper.updatePermition(driver);
	}

}
