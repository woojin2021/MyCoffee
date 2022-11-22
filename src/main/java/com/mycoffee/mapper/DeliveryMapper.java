package com.mycoffee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mycoffee.domain.DriverOrderVO;

public interface DeliveryMapper {

	public List<DriverOrderVO> selectDriverOrderList(String did);

	public int updateOrderToCheckout(@Param("oid") String oid, @Param("did") String did);

	public int updateOrderToComplete(@Param("oid") String oid, @Param("did") String did);
	
	public List<DriverOrderVO> selectUncheckedOrderList();
}
