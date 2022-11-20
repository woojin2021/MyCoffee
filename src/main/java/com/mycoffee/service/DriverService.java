package com.mycoffee.service;


import java.util.List;

import com.mycoffee.domain.DriverDTO;
import com.mycoffee.domain.DriverInfo;
import com.mycoffee.domain.DriverSummaryVO;
import com.mycoffee.domain.DriverVO;

public interface DriverService {
	
	/* 회원가입 */
	public int memberJoin(DriverDTO driver) throws Exception;

	/* 회원 정보 검색 */
	public DriverVO getDriver(String did);
	
	/* 회원 정보 수정 */
	public boolean modify(DriverDTO driver);
	
	/* 회원 정보 삭제 */
	public boolean delete(String did);
	
	/* 아이디 중복 검사 */
	public int idCheck(String did) throws Exception;
	
	/*로그인*/
	public DriverVO driverLogin(DriverDTO driver) throws Exception;

	/* 근무 상태 */
	public boolean workDriver(String did);
	
	/* 근무 시작*/
	public boolean startDriver(String did);
	
	/* 근무 종료*/
	public boolean endDriver(String did);
	
	/* 배달원 주문 확인 */
	public List<DriverInfo> getOrder(String did);
	
//	public DriverVO driverLogin(String did, String password);
	
//	public boolean driverStatus(DriverInfo driver);

}
