package com.mycoffee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mycoffee.domain.OrderDetailVO;

public interface OrderDetailMapper {

	// --------------------------- tbl_order_detail ---------------------------
	
	// insert
	public void insertOrderdetail(@Param("oid") String oid, @Param("pid") String pid, @Param("price") int price);

	// select
	public OrderDetailVO selectDetail(@Param("oid") String oid, @Param("pid") String pid);

	public List<OrderDetailVO> selectDetailList(String oid);

	public int getPieces(@Param("oid") String oid, @Param("pid") String pid);

//	public List<String> getpidList(String oid);

	// update
	public void updatePieces(@Param("oid") String oid, @Param("pid") String pid, @Param("num") int num);

	// delete
	public int deleteOrderDetailByOid(String oid);

	public int deleteOrderDetail(@Param("oid") String oid, @Param("pid") String pid);
}
