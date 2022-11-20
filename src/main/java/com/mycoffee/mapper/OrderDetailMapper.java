package com.mycoffee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mycoffee.domain.Order_detailVO;

public interface OrderDetailMapper {

	// --------------------------- tbl_order_detail ---------------------------
	
	// insert
	public void insertOrderdetail(@Param("oid") String oid, @Param("pid") String pid, @Param("price") int price);

	// select
	public Order_detailVO getodlist(@Param("oid") String oid, @Param("pid") String pid);

	public Order_detailVO select_detail(String oid);

	public List<Order_detailVO> select_detailList(String oid);

	public Order_detailVO select_detail2(@Param("oid") String oid, @Param("pid") String pid);

	public int getpieces(@Param("oid") String oid, @Param("pid") String pid);

	public List<String> getpidList(String oid);

	// delete
	public void deleteorder_detail(String oid);

}
