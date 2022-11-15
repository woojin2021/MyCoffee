package com.mycoffee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mycoffee.domain.OrderOfTodyVO;

public interface OrderViewMapper {

	public List<OrderOfTodyVO> getRawOrderList();
	
	public int updateOrderStatus(@Param("oid") String oid, @Param("status") int status);
}
