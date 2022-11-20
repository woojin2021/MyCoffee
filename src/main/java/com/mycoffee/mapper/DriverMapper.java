package com.mycoffee.mapper;

import java.util.List;

import com.mycoffee.domain.DriverDTO;
import com.mycoffee.domain.DriverSummaryVO;
import com.mycoffee.domain.DriverVO;

public interface DriverMapper {
	
	/* 회원가입 */
	public int insert(DriverDTO did);

	public DriverVO select(String did);

	public int update(DriverDTO driver);

	public int delete(String did);

	public DriverVO selectDriver(String did);

	/* 배달원 로그인 */
	public DriverVO driverLogin(DriverDTO driver);
	
	// 파라미터 문제로 사용불가
//	public DriverVO driverLogin(String did, String password);

	public int updatePermition(DriverDTO dvo);

	/* onwork update */
	public int updateOnWork(DriverDTO dvo);
	
	/* 아이디 중복 검사 */
	public int idCheck(String did);

	public List<DriverSummaryVO> selectDriverSummaryList();

}
