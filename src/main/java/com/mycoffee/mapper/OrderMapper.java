package com.mycoffee.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.mycoffee.domain.OrderVO;
import com.mycoffee.domain.OrderWithDetailVO;

public interface OrderMapper {

	// --------------------------- tbl_order ---------------------------
	
	// insert
	public void insertOrder(@Param("oid") String oid, @Param("userid") String userid,
			@Param("totalprice") int totalprice, @Param("status") int status);

	// select
	public OrderVO selectstatus0(String userid);

	// select list
	public List<OrderVO> getlist2(String userid);

	// select count
	public int countlist(String userid);

	public int countstatus(@Param("userid") String userid, @Param("status") int status);

//	public int countstatus2(String userid);

	// update
	public void updatTotalprice(@Param("oid") String oid, @Param("addprice") int addprice);

	public void updateStatus(@Param("oid") String oid, @Param("status") int status);

	public void updateOrderdate(@Param("oid") String oid);

	// delete
	public int deleteOrder(@Param("oid") String oid, @Param("status") int status);


	
	// --------------------------- tbl_order + tbl_order_detail ---------------------------
	public List<OrderWithDetailVO> selectOrderDatas(@Param("userid") String userid, @Param("status") int status);

}
