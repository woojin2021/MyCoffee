package com.mycoffee.mapper;

import java.util.List;

import com.mycoffee.domain.DriverInfo;
import com.mycoffee.domain.DriverOrderDTO;
import com.mycoffee.domain.DriverVO;

public interface DriverMapper {

	public DriverVO select(String did);
	
	/* 회원가입 */
	public void insert(DriverVO did);
	
	/* 아이디 중복 검사 */
	public int idCheck(String did);
	
	/* 배달원 로그인 */
	public DriverVO driverLogin(DriverVO driver);
	
	/* onwork */
	public int getDriveronWork();
	
	/* onwork update */
	public int updateStartDriver(DriverVO dvo);
	
	public List<DriverOrderDTO> getOrder(String did);
	
	public DriverVO selectDriver(String did);

	public int updatePermition(DriverVO dvo);

	public DriverVO driverLogin(String did, String password);

	public boolean updateDriver(DriverVO driver);

	public boolean deleteDriver(String did);
	
	public int updateStatus(DriverInfo dvo);
	
}
