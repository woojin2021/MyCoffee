package com.mycoffee.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.mycoffee.domain.OrderVO;

public interface OrderMapper {

	// --------------------------- tbl_order ---------------------------
	
	// insert
	public void insertOrder(@Param("oid") String oid, @Param("userid") String userid,
			@Param("totalprice") int totalprice, @Param("status") int status);

	// select
	public OrderVO get(String oid);

	public OrderVO selectstatus0(String userid);

	// select list
	public List<OrderVO> getlist();

	public List<OrderVO> getlist2(String userid);

	public List<OrderVO> selectstatus0List(String userid);

	// select count
	public int countlist(String userid);

	public int countstatus(@Param("userid") String userid, @Param("status") int status);

	public int countstatus2(String userid);

	// update
	public void piecesupdate(@Param("oid") String oid, @Param("pid") String pid, @Param("num") int num);

	public void totalpriceupdate(@Param("oid") String oid, @Param("addprice") int addprice);

	public void statusupdate(@Param("oid") String oid, @Param("status") int status);

	public void orderdateupdate(@Param("oid") String oid);

	// delete
	public void deleteorder(@Param("oid") String oid, @Param("status") int status);

	

}
