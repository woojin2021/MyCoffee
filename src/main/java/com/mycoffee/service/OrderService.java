package com.mycoffee.service;


import java.util.List;


import com.mycoffee.domain.OrderData;

public interface OrderService {
	
	/**
	 * 장바구니에 상품 추가
	 * @param userid
	 * @param pid
	 * @return
	 */
	public int addProductToCart(String userid, String pid);
	/**
	 * 장바구니 확인
	 * @param userid
	 * @return
	 */
	public OrderData getCart(String userid);
	/**
	 * 상품 수량 변경
	 * @param userid
	 * @param oper plus:+1, minus:-1
	 * @param pid
	 */
	public void changeProductPieces(String userid, String oper, String pid);
	/**
	 * 장바구니 삭제
	 * @param userid
	 * @return
	 */
	public int deleteCart(String userid);
	/**
	 * 주문 완료
	 * @param userid
	 * @return
	 */
	public int confirmOrder(String userid);
	/**
	 * 주문 이력
	 * @param userid
	 * @return
	 */
	public List<OrderData> getOrderHistory(String userid);
}
