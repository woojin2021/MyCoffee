package com.mycoffee.mapper;

import java.util.List;

import com.mycoffee.domain.CodesVO;

public interface CodesMapper {
	
	public List<CodesVO> getCodeList(String type);

}
