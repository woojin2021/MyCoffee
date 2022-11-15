package com.mycoffee.controller;

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
import com.mycoffee.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
@WebAppConfiguration
@Controller
@Log4j
@RequestMapping("/user/*")
@AllArgsConstructor
public class UserController {
	private UserService service;
	
	 @GetMapping({"/User_Main_Home","/User_SignUp","/User_Edit","User_Login"}) 
	  public void Nomarl() 
	 {
		 
		 
	 }
	 @GetMapping("/alert")
	 public String msg(String msg)
	 {
		 if(msg =="1")
		 {
			 return "redirect:/user/User_Login?msg=1";
		 }
		 else if(msg =="2")
		 {
			 return "redirect:/user/User_Login?msg=2";
		 }
		 else
		 {
			 return "redirect:/user/User_Login?msg=3";
		 }
	}
	//로그인 햇는지 세션으로 확인하기
	@GetMapping("/CheckSession")
	public String CheckSession(String str,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session != null)
		{
			String str2="redirect:/user/"+str;
			return str2;
		}
		else
		{
			return "redirect:/user/User_Drink_Menu";
		}
	}
	//장바구니 담기 버튼에서만 현재 사용중 
	@GetMapping("/CheckSession2")
	public String CheckSession2(@RequestParam("str")String str,@RequestParam("category")String category,@RequestParam("tem")int tem,@RequestParam("cap")int cap,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
			
		if(session.getAttribute("sessionId") == null)			
			return "redirect:/user/User_Login";
					
		return "redirect:/user/"+str +"?category="+category+"&tem="+tem+"&cap="+cap;
	}
	@PostMapping("/login")
	public String login(@RequestParam("userid") String id,@RequestParam("password") String passwd, Model model,HttpServletRequest request)
	{
		log.info("login"+id+passwd);
		HttpSession session = request.getSession();
		log.info(service.LoginUser(id,passwd));
		UserVO user1= new UserVO();
		user1 =service.LoginUser(id,passwd);
		log.info(user1);
		if(user1==null)
		{
			session.setAttribute("sessionId", null);
			return "redirect:/user/User_Login?msg=1";
		}
		else
		{
			session.setAttribute("sessionId", user1);
			log.info("signup.."+user1.getUserid());
			return "redirect:/user/User_Drink_Menu";
		}
	}
	
	//아이디 비밀번호 체크
	@PostMapping("/logincheck")
	public String logincheck(@RequestParam("userid") String id,@RequestParam("password") String passwd)
	{
		log.info(service.LoginUser(id,passwd));
		int i_checkid = service.CheckId(id);
		int i_checkuser = service.CheckUser(id, passwd);
		UserVO user1= service.LoginUser(id,passwd);
		log.info(user1);
		if(i_checkid ==0 && i_checkuser ==0)//아이디마저 없을때
		{
			return "redirect:/user/alert?msg=1";
		}
		else if(i_checkid ==1 && i_checkuser ==0)//아이디가 있을때 비밀번호가 없을때
		{
			return "redirect:/user/alert?msg=2";
		}
		else if(i_checkid==1 && i_checkuser==1)//유저가 있을때
		{
			String strid=id;
			String strpasswd=passwd;
			return "redirect:/user/GetSession?id="+strid+"&passwd="+strpasswd;
		}
		else
		{
			return "redirect:/user/User_Login";
		}
	}
	//아이디 비밀번호 있으면 세션 얻기
	@GetMapping("/GetSession")
	public String GetSession(@RequestParam("id") String id,@RequestParam("passwd") String passwd,HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		UserVO user=service.LoginUser(id,passwd);
		session.setAttribute("sessionId",user);
		return "redirect:/user/User_Drink_Menu";
	}
	//로그아웃해서 세션도 없애기
	@GetMapping("/logout")
	public String logout(HttpServletRequest request)
	{
		log.info("logout..");
		HttpSession session = request.getSession(false);
		if(session != null)
			session.invalidate();
		return "redirect:/user/User_Drink_Menu";
	}
	//회원가입
	@PostMapping("/signup")
	public String signup(UserVO user,RedirectAttributes rttr)
	{
		log.info("signup..");
		service.insertUser(user);
		rttr.addFlashAttribute("result", user.getUserid());
		return "redirect:/user/User_Drink_Menu";
	}
	//회원 정보 수정
	@PostMapping("/useredit")
	public String useredit(UserVO user ,HttpServletRequest request)
	{
		//검색
		log.info(user);
		//model.addAttribute("user",service.CheckId(user.getUserid()));
		//검색결과 true면 해당 회원 정부 수정
		if(service.modify(user))
		{
			log.info(user);
			HttpSession session = request.getSession(false);
			log.info(session);
			session.setAttribute("sessionId",user);
			return "redirect:/user/User_Drink_Menu";
		}
		return "redirect:/user/User_Drink_Menu";
	}
	//회원 탈퇴
	@GetMapping("/userdelete")
	public String userdelete(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		UserVO user = (UserVO)session.getAttribute("sessionId");
		//검색
		if(service.CheckUser(user.getUserid(), user.getPassword())==1)
		{
			service.remove(user.getUserid());
			return "redirect:/user/logout";
		}
		//검색결과 true면 해당 회원 탈퇴
		return "redirect:/user/User_Drink_Menu";
	}

}
