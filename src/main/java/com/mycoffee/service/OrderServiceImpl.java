package com.mycoffee.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mycoffee.domain.OrderVO;
import com.mycoffee.domain.OrderWithDetailVO;
import com.mycoffee.domain.ProductJoinVO;
import com.mycoffee.domain.OrderData;
import com.mycoffee.mapper.OrderDetailMapper;
import com.mycoffee.mapper.OrderMapper;
import com.mycoffee.mapper.ProductViewMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
	private OrderMapper mapper;
	private OrderDetailMapper detailmapper;
	private ProductViewMapper pmapper;

//	@Override
//	public void insertOrder(String oid, String userid, int totalprice, int status) {
//		mapper.insertOrder(oid, userid, totalprice, status);
//	}

	@Override
	public int deleteCart(String userid) {
		OrderVO order = mapper.selectstatus0(userid);
		if (order == null) {
			return 0;
		} else {
			String oid = order.getOid();
			detailmapper.deleteOrderDetailByOid(oid);
			return mapper.deleteOrder(oid, 0);
		}
	}

//	@Override
//	public void deleteOrderDetail(String oid) {
//		detailmapper.deleteorder_detail(oid);
//	}
//	@Override
//	public int updateorder(OrderVO order) {
//		mapper.update();
//		return 0;
//	}
//	@Override
//	public OrderVO get() {
//		mapper.get(null);
//		return null;
//	}

//	@Override
//	public List<OrderVO> getlist() {
//		mapper.getlist();
//		return null;
//	}

//	@Override
//	public List<OrderVO> getlist2(String userid) {
//		return mapper.getlist2(userid);
//	}
//	@Override
//	public int countlist(String userid) {
//		return mapper.countlist(userid);
//	}

//	@Override
//	public int countstatus(String userid, int status) {
//		return mapper.countstatus(userid, status);
//	}

//	@Override
//	public int countstatus2(String userid) {
//		return mapper.countstatus2(userid);
//	}

//	@Override
//	public OrderVO selectstatus0(String userid) {
//		return mapper.selectstatus0(userid);
//	}

//	@Override
//	public List<OrderVO> selectstatus0List(String userid) {
//		return mapper.selectstatus0List(userid);
//	}

	// detail
//	@Override
//	public List<OrderDetailVO> getodlist(String oid, String pid) {
//		return (List<OrderDetailVO>) detailmapper.getodlist(oid, pid);
//	}

//	@Override
//	public void insertOrder_detail(String oid, String pid, int price) {
//		detailmapper.insertOrderdetail(oid, pid, price);
//	}

//	@Override
//	public OrderDetailVO selectstatus_detail(String oid) {
//		return detailmapper.select_detail(oid);
//	}

//	@Override
//	public List<OrderDetailVO> selectstatus_detailList(String oid) {
//		return detailmapper.select_detailList(oid);
//	}

//	@Override
//	public OrderDetailVO selectstatus_detail2(String oid, String pid) {
//		return detailmapper.select_detail2(oid, pid);
//	}

//	@Override
//	public int getpieces(String oid, String pid) {
//		return detailmapper.getpieces(oid, pid);
//	}

//	@Override
//	public List<String> getpidList(String oid) {
//		return detailmapper.getpidList(oid);
//	}

	@Override
	public List<OrderData> getOrderHistory(String userid) {
		List<OrderData> orders = getOrderData(userid, -1);
		log.info("getOrderHistory: " + orders.size());
		return orders;
	}

	@Override
	public OrderData getCart(String userid) {
		List<OrderData> orders = getOrderData(userid, 0);
		log.info("getCart: " + orders.size());
		if (orders.size() > 0) {
			log.info("getCart:detail: " + orders.get(0).getDetails().size());
			return orders.get(0);
		}
		return null;
	}

	/**
	 * ???????????? ??????
	 * 
	 * @param userid
	 * @param status 0:????????????, -1: ????????????
	 * @return
	 */
	private List<OrderData> getOrderData(String userid, int status) {
		List<OrderData> orders = new ArrayList<>();
		List<OrderWithDetailVO> datas = mapper.selectOrderDatas(userid, status);
		String oid = "";
		OrderData order = null;
		OrderData.OrderDetailData detail;
		for (OrderWithDetailVO data : datas) {
			// ?????? ??????
			if (oid.equals(data.getOid()) == false) {
				order = new OrderData();
				order.setOid(data.getOid());
				order.setUserid(data.getUserid());
				order.setStatus(data.getStatus());
				order.setStatusDisp(data.getStatusDisp());
				order.setTotalprice(data.getTotalprice());
				order.setOrderdate(data.getOrderdate());
				order.setRegistdate(data.getRegistdate());
				order.setDetails(new ArrayList<>());
				orders.add(order);
				oid = order.getOid();
			}
			// ?????? ?????? ??????
			detail = order.newDetail();
			detail.setPcategory(data.getPcategory());
			detail.setPid(data.getPid());
			detail.setPname(data.getPname());
			detail.setPrice(data.getPrice());
			detail.setPieces(data.getPieces());
			detail.setTemperature(data.getTemperature());
			detail.setTemperatureDisp(data.getTemperatureDisp());
			detail.setCapacity(data.getCapacity());
			detail.setCapacityDisp(data.getCapacityDisp());
			order.getDetails().add(detail);
		}

		return orders;
	}

	@Override
	public void changeProductPieces(String userid, String oper, String pid) {
		OrderVO order = mapper.selectstatus0(userid);// status0??? oid??? ??????
		String oid = order.getOid();
		ProductJoinVO p = pmapper.selectProductByPid(pid);
		int pieces = detailmapper.getPieces(oid, pid);
		int addprice = 0;
		if (oper.equals("plus")) {
			addprice = order.getTotalprice() + p.getPrice();
			detailmapper.updatePieces(oid, pid, ++pieces);
			mapper.updatTotalprice(oid, addprice);
		} else {
			if (pieces == 0) {
				// delete ?????? ?????????
			} else// ????????? 0??? ?????????
			{
				// ???????????????
				addprice = order.getTotalprice() - p.getPrice();
				detailmapper.updatePieces(mapper.selectstatus0(userid).getOid(), pid, --pieces);
				mapper.updatTotalprice(oid, addprice);
			}
		}
	}

	@Override
	public int addProductToCart(String userid, String pid) {
		log.info("addProductToCart: " + pid);
		ProductJoinVO p = pmapper.selectProductByPid(pid); // ?????? ??????
		OrderData cart = getCart(userid); // ????????????
		String oid = null; // ?????? id
		int totalPrice = 0; // ?????? ??????
		
		// ???????????? ??????
		if (cart == null) {
			// ???????????? ??????
			Date now = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			oid = formatter.format(now);
			mapper.insertOrder(oid, userid, p.getPrice(), 0);
			
		} else {
			for(OrderData.OrderDetailData detail : cart.getDetails()) {
				if (detail.getPid().equals(pid)) {
					return 0; // ?????? ????????? ??????
				}
			}
			oid = cart.getOid();
			totalPrice = cart.getTotalprice();
		}
		// ??????????????? ??????
		detailmapper.insertOrderdetail(oid, p.getPid(), p.getPrice());
		// ?????? ??????
		totalPrice += p.getPrice();
		mapper.updatTotalprice(oid, totalPrice);
		
		return 1;
	}

	@Override
	public int confirmOrder(String userid) {
		String oid = mapper.selectstatus0(userid).getOid();//status0??? oid
		mapper.updateStatus(oid, 1);//?????? ??????0->?????? ??????
		//??????????????? order????????? ??? ?????????.
		mapper.updateOrderdate(oid);
		return 0;
	}

}
