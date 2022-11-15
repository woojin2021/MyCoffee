package com.mycoffee.mapper;

import com.mycoffee.domain.CafeDTO;

public interface CafeMapper {

	public CafeDTO selectCafe(int cafeid);
	
	public int updateCafeOpen(CafeDTO dto);
	
	public int getFreeDriverCount();
	
	public int getWaitingOrderCount();
	
}
