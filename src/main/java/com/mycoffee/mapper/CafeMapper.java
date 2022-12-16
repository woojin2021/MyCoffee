package com.mycoffee.mapper;

import org.apache.ibatis.annotations.Param;

import com.mycoffee.domain.CafeDTO;

public interface CafeMapper {

	public CafeDTO selectCafe(int cafeid);
	
	public int updateCafeOpen(CafeDTO dto);
	
	public int getFreeDriverCount();
	
	public int getWaitingOrderCount();
	
	public int setToken(@Param("userid") String userid, @Param("token") String token);

	public int updateToken(String token);
	
	public Integer isTokenExpired(String token);
}
