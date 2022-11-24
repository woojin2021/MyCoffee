package com.mycoffee.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycoffee.domain.UserVO;
import com.mycoffee.domain.OrderVO;
import com.mycoffee.domain.OrderDetailVO;
import com.mycoffee.service.OrderService;
import com.mycoffee.domain.ProductJoinVO;
import com.mycoffee.service.ProductService;

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
	ProductService pservice;

	@PostMapping("/User_Order")
	public String get2(HttpServletRequest request)
	{
		String category=request.getParameter("category");
		String tem=request.getParameter("tem");
		String cap=request.getParameter("cap");
		return "redirect:/user/CheckSession2?str=InsertOrder&category="+category+"&tem="+tem +"&cap="+cap;
		
	}
	
	
	@PostMapping("/InsertOrder")
	public String insertorder(@RequestParam("pid") String pid, Model model, HttpSession session)
	{ 
		if(session.getAttribute("sessionId") == null)
			return "redirect:/user/User_Login";
		//1현재 오더 중에 주문 작성(0) 상태가 있다면 oid 생성 x
		//-리스트로 확인하고 주문작성 상태인 주문 가져오기
		//usrid로 검색해서 찾기
		//2주문내역 자체가 없는 경우 당연히 oid 없을테니 생성
		//suerid로 검색하고 count값 받아오기
		//3주문내역은 있지만 주문 작성(0)상태가 없는 경우 oid 생성
		//4주문내역도 없고 주문작성상태도 없는 경우?
		//userid로 검색하고 없으면
//		ProductJoinVO p =pservice.getProductByPK(category, tem, cap);//프로덕트 검색
		ProductJoinVO p =pservice.getProductByPid(pid);//프로덕트 검색
//		HttpSession session = request.getSession(false);//세션 확인
		UserVO user = (UserVO)session.getAttribute("sessionId");//유저값 세션으로 가져오기
		int addprice=0;
		//2.주문내역 자체가 없는경우 oid 생성 필요
		if(service.countlist(user.getUserid()) ==0)
		{
			Date now = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String oid = formatter.format(now);
			service.insertOrder(oid,user.getUserid(),p.getPrice(),0);
			service.insertOrder_detail(oid, p.getPid(), p.getPrice());
			return "redirect:/menu/User_Drink_Menu";
		}
		//1.주문내역이 있고 현재 오더 중에 주문 작성(0) 상태가 있다면 oid 생성 x
		else if(service.countlist(user.getUserid()) !=0 &&service.countstatus(user.getUserid(), 0) !=0)
		{
			OrderVO order =service.selectstatus0(user.getUserid());
			
			//이미 주문한 pid일 경우 넘겨받기.
			System.out.println(service.selectstatus_detailList(order.getOid()));
			List<OrderDetailVO> otlist = new ArrayList<OrderDetailVO>();
			List<String> pidlist = new ArrayList<String>();
			boolean checkpid = false;
			for(int i=0; i<service.selectstatus_detailList(order.getOid()).size();i++)
			{
				pidlist.add(service.selectstatus_detailList(order.getOid()).get(i).getPid());
			}
			for(int i=0; i<pidlist.size();i++)
			{
				if(pidlist.get(i).equals(p.getPid()))
				{
					checkpid = false;
				}
				else
					checkpid = true;
			}
			//if(service.selectstatus_detail(order.getOid()).getPid()!=p.getPid())
			if(checkpid==true)
			{
				service.insertOrder_detail(order.getOid(), p.getPid(), p.getPrice());
				addprice=order.getTotalprice()+p.getPrice();
				service.totalpriceupdate(order.getOid(), addprice);
				return "redirect:/menu/User_Drink_Menu";
			}
			return "redirect:/menu/User_Drink_Menu";
		}
		//3.주문내역이 있고 오더 중 주문 작성 상태가 없어서 oid 생성 필요
		else if(service.countlist(user.getUserid()) !=0 &&service.countstatus(user.getUserid(), 0) ==0)
		{
			Date now = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String oid = formatter.format(now);
			System.out.println(oid);
			service.insertOrder(oid,user.getUserid(),p.getPrice(),0);
			service.insertOrder_detail(oid, p.getPid(), p.getPrice());
			return "redirect:/menu/User_Drink_Menu";
		}
		return "redirect:/menu/User_Drink_Menu";
	}
	//장바구니 들어갈때
	@GetMapping("/User_Shopping_Basket")
	public void SelectOrderStatus0(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);//세션 확인
		UserVO user = (UserVO)session.getAttribute("sessionId");//유저값 세션으로 가져오기
		if(service.selectstatus0(user.getUserid()) == null)
		{
			System.out.println("아무것도 없는데요");
		}
		else if(service.countstatus(user.getUserid(), 0)!=0)
		{
			//System.out.println("가나다라가나다라다나가1"+service.getpidList(oid));
			//service.getpidList(service.selectstatus0List(user.getUserid()).get(0).getOid()); //pid리스트 가져옴.
			//model.addAttribute("order",service.selectstatus0(user.getUserid()));
			//model.addAttribute("od",service.selectstatus_detail(service.selectstatus0(user.getUserid()).getOid()));
			//request.setAttribute("order2", service.selectstatus0(user.getUserid()));
			//request.setAttribute("od2", service.selectstatus_detail(service.selectstatus0(user.getUserid()).getOid()));
			//request.setAttribute("od2", service.selectstatus_detailList(service.selectstatus0(user.getUserid()).getOid()));
			//request.setAttribute("product", pservice.get3(service.selectstatus_detail(service.selectstatus0(user.getUserid()).getOid()).getPid()));
			
			String oid = service.selectstatus0(user.getUserid()).getOid();//오더 아이디 구하기
			List<String> pidlist = service.getpidList(oid);//오더아이디로 프로덕트 아이디 리시트 구하기
			request.setAttribute("order2", service.selectstatus0List(user.getUserid()));
			request.setAttribute("od2", service.selectstatus_detailList(service.selectstatus0List(user.getUserid()).get(0).getOid()));
			List<ProductJoinVO> plist = new ArrayList<ProductJoinVO>();
			int index =0;
			while(pidlist.get(index) !=null)
			{
				plist.add(pservice.getProductByPid(pidlist.get(index)));
				index++;
				if(pidlist.size() == index)
				{index =0;break;}
			}
			request.setAttribute("product",plist);
		}
	}
	
	@GetMapping("/piecesChange")
	public String piecesChage(@RequestParam("str")String str,@RequestParam("category")String category,@RequestParam("tem")int tem,@RequestParam("cap")int cap,@RequestParam("pid")String pid, HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);//세션 확인
		UserVO user = (UserVO)session.getAttribute("sessionId");//유저아이디
		service.selectstatus0(user.getUserid()).getOid();
		String oid=service.selectstatus0(user.getUserid()).getOid();//status0인 oid
		OrderVO order =service.selectstatus0(user.getUserid());//status0인 oid로 오더
		ProductJoinVO p = pservice.getProductByPK(category, tem, cap);
		int i=service.getpieces(service.selectstatus0(user.getUserid()).getOid(),pid);
		int a = i+1;
		int b = i-1;
		int addprice=0;
		if(str.equals("plus"))
		{
			addprice=order.getTotalprice()+p.getPrice();
			service.piecesupdate(service.selectstatus0(user.getUserid()).getOid(), pid, a);
			service.totalpriceupdate(oid, addprice);
			return "redirect:/user/CheckSession?str=User_Shopping_Basket";
		}
		else
		{
			if(service.selectstatus_detail2(service.selectstatus0(user.getUserid()).getOid(), pid).getPieces()== 0)
			{
				//delete 할수 있도록
				return "redirect:/user/CheckSession?str=User_Shopping_Basket";
			}
			else//갯수가 0이 아닐때
			{
				//정상적으로
				addprice = order.getTotalprice()-p.getPrice();
				service.piecesupdate(service.selectstatus0(user.getUserid()).getOid(), pid, b);
				service.totalpriceupdate(oid, addprice);
				return "redirect:/user/CheckSession?str=User_Shopping_Basket";
			}
		}
	}
	
	
	//주문 최종
	@GetMapping("/Last_Order")
	public String Order(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);//세션 확인
		UserVO user = (UserVO)session.getAttribute("sessionId");//유저아이디
		service.selectstatus0(user.getUserid()).getOid();
		String oid=service.selectstatus0(user.getUserid()).getOid();//status0인 oid
		service.statusupdate(oid, 1);//주문 작성0->주문 완료
		//주문완료시 order데이트 값 줘야함.
		service.orderdateupdate(oid);
		
		return "redirect:/menu/User_Drink_Menu";
	}
	
	//장바구니 내용 취소
	@GetMapping("/Order_0_Cancel")
	public String Order_0_Cancel(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);//세션 확인
		UserVO user = (UserVO)session.getAttribute("sessionId");//유저아이디
		service.deleteorder_detail(service.selectstatus0(user.getUserid()).getOid());
		service.deleteorder(service.selectstatus0(user.getUserid()).getOid(), 0);
		return "redirect:/user/User_Shopping_Basket";
	}
	
	//주문 내역 들어가기
	@GetMapping("/User_Order_History")
	public void User_Order_History(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);//세션 확인
		UserVO user = (UserVO)session.getAttribute("sessionId");//유저값 세션으로 가져오기
		if(service.countstatus2(user.getUserid())!=0)
		{
			//orderlist
			List<OrderVO> order =service.getlist2(user.getUserid());
			request.setAttribute("order", order);
			
			//orderdetaillist
			List<OrderDetailVO>otlist = new ArrayList<OrderDetailVO>();
			int index =0;
			while(order.get(index)!=null)
			{
				List<OrderDetailVO>otlisttmp=service.selectstatus_detailList(order.get(index).getOid());
				for(int i=0;i<otlisttmp.size();i++)
				{
					otlist.add(otlisttmp.get(i));
				}
				index++;
				if(order.size() == index)
				{index =0;break;}
			}
			request.setAttribute("otlist", otlist);
			
			//oid리스트
			List<String> oidList = new ArrayList<String>();
			while((service.getlist2(user.getUserid()).get(index)) != null)
			{
				oidList.add(service.getlist2(user.getUserid()).get(index).getOid());
				index++;
				if((service.getlist2(user.getUserid()).size()) == index)
				{index =0;break;}
			}

			
			//pidlist
			
			List<String> pidlist = new ArrayList<String>();

			//service.getpidList(oid);//오더아이디로 프로덕트 아이디 리시트 구하기
			while(oidList.get(index)!= null)
			{
				pidlist.addAll(service.getpidList(oidList.get(index)));
				index++;
				if(oidList.size() == index)
				{index =0;break;}
			}
			
			List<ProductJoinVO> plist = new ArrayList<ProductJoinVO>();
			//product리스트
			while(pidlist.get(index) !=null)
			{
				plist.add(pservice.getProductByPid(pidlist.get(index)));
				index++;
				if(pidlist.size() == index)
				{index =0;break;}
			}
			request.setAttribute("product",plist);
		}
	}
}
