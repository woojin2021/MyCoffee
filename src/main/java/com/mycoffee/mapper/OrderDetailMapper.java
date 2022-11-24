package com.mycoffee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mycoffee.domain.OrderDetailVO;

public interface OrderDetailMapper {

	// --------------------------- tbl_order_detail ---------------------------
	
	// insert
	public void insertOrderdetail(@Param("oid") String oid, @Param("pid") String pid, @Param("price") int price);

	// select
	public OrderDetailVO getodlist(@Param("oid") String oid, @Param("pid") String pid);

	public OrderDetailVO select_detail(String oid);

	public List<OrderDetailVO> select_detailList(String oid);

	public OrderDetailVO select_detail2(@Param("oid") String oid, @Param("pid") String pid);

	public int getpieces(@Param("oid") String oid, @Param("pid") String pid);

	public List<String> getpidList(String oid);

	// delete
	public void deleteorder_detail(String oid);

}
