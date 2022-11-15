package com.mycoffee.service;


import java.util.List;

import com.mycoffee.domain.DriverInfo;
import com.mycoffee.domain.DriverOrderDTO;
import com.mycoffee.domain.DriverVO;
import com.mycoffee.domain.OrderrInfo;

public interface DriverService {
	
	/* 회원가입 */
	public void memberJoin(DriverVO driver) throws Exception;

	/* 아이디 중복 검사 */
	public int idCheck(String did) throws Exception;
	
	/*로그인*/
	public DriverVO driverLogin(DriverVO driver) throws Exception;
	
	/*onwork*/
	public int getDriveronWork();
	
	public boolean workDriver(String did);
	
	/*근무시작*/
	public boolean startDriver(String did);
	
	/*근무종료*/
	public boolean endDriver(String did);
	
	/* 배달원 주문 확인 */
	public List<DriverInfo> getOrder(String did);
	
	public DriverVO getDriver(String did);
	
	/* 등록 승인 */
	public void approveRegist(DriverVO driver);

	/* 등록 거부 */
	public void rejectRegist(DriverVO driver);

	public DriverVO driverLogin(String did, String password);
	
	/* 회원 정보 수정 */
	public boolean modify(DriverVO driver);
	
	/* 회원 정보 삭제 */
	public boolean delete(String did);
	
//	public boolean driverStatus(DriverInfo driver);
	
}
