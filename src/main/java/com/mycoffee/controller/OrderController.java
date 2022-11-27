package com.mycoffee.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycoffee.domain.UserVO;
import com.mycoffee.service.OrderService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
@WebAppConfiguration
@Controller
@Log4j
@RequestMapping("/user/*")
@AllArgsConstructor
public class OrderController 
{
	OrderService service;

//	@PostMapping("/User_Order")
//	public String get2(HttpServletRequest request)
//	{
//		String category=request.getParameter("category");
//		String tem=request.getParameter("tem");
//		String cap=request.getParameter("cap");
//		
//		return "redirect:/user/CheckSession2?str=InsertOrder&category="+category+"&tem="+tem +"&cap="+cap;
//	}
	
	@PostMapping("/InsertOrder")
	public String addProductToCart(@RequestParam("pid") String pid, Model model, HttpSession session)
	{ 
		UserVO user = (UserVO)session.getAttribute("sessionId");//유저값 세션으로 가져오기
		service.addProductToCart(user.getUserid(), pid);
		return "redirect:/menu/User_Drink_Menu";
	}
	
	//장바구니 들어갈때
	@GetMapping("/User_Shopping_Basket")
	public void showCart(HttpSession session, Model model)
	{
		UserVO user = (UserVO)session.getAttribute("sessionId");//유저값 세션으로 가져오기
		model.addAttribute("orderData", service.getCart(user.getUserid()));
	}
	
	@GetMapping("/piecesChange")
	public String piecesChage(@RequestParam("oper")String oper, @RequestParam("pid")String pid, HttpSession session, Model model)
	{
		UserVO user = (UserVO)session.getAttribute("sessionId");//유저아이디
		service.changeProductPieces(user.getUserid(), oper, pid);
		return "redirect: /user/User_Shopping_Basket";
	}
	
	//주문 최종
	@GetMapping("/Last_Order")
	public String confirmOrder(HttpSession session)
	{
		UserVO user = (UserVO)session.getAttribute("sessionId");//유저아이디
		service.confirmOrder(user.getUserid());
		
		return "redirect:/menu/User_Drink_Menu";
	}
	
	//장바구니 내용 취소
	@GetMapping("/Order_0_Cancel")
	public String deleteCart(HttpSession session)
	{
		UserVO user = (UserVO)session.getAttribute("sessionId");//유저아이디
		service.deleteCart(user.getUserid());
		
		return "redirect:/user/User_Shopping_Basket";
	}
	
	//주문 내역 들어가기
	@GetMapping("/User_Order_History")
	public void getOrderHistory(HttpSession session, Model model)
	{
		UserVO user = (UserVO)session.getAttribute("sessionId");//유저값 세션으로 가져오기
		model.addAttribute("orderHistory", service.getOrderHistory(user.getUserid()));
	}
}
