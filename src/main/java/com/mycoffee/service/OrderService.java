package com.mycoffee.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mycoffee.domain.OrderVO;
import com.mycoffee.domain.Order_detailVO;
public interface OrderService {
	public void insertOrder(String oid, String userid, int totalprice,int status);
	public int deleteorder(String oid, int status);
	public int updateorder(OrderVO order);
	public int countlist(String userid);
	public int countstatus(String userid, int status);
	public int countstatus2(String userid);
	public OrderVO selectstatus0(String userid);
	public List<OrderVO> selectstatus0List(String userid);
	public OrderVO get();
	public List<OrderVO> getlist();
	public List<OrderVO> getlist2(String oid);
	//orderdetail
	public void insertOrder_detail(String oid,String pid, int price);
	public List<Order_detailVO> getodlist(String oid, String pid);
	public List<Order_detailVO> selectstatus_detailList(String oid);
	public Order_detailVO selectstatus_detail(String oid);
	public Order_detailVO selectstatus_detail2(String oid, String pid);
	public int getpieces(@Param("oid")String oid,@Param("pid")String pid);
	public List<String> getpidList(String oid);
	
	public void piecesupdate(String oid, String pid, int num);
	public void totalpriceupdate(@Param("oid")String oid, @Param("addprice")int addprice);
	public void statusupdate(String oid, int status);
	public void orderdateupdate(String userid);
	
	public void deleteorder_detail(String oid);
}
