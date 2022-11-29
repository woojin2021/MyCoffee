package com.mycoffee.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycoffee.common.Auth;
import com.mycoffee.common.Auth.Role;
import com.mycoffee.common.LoginChecker;
import com.mycoffee.domain.UserDTO;
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

	@GetMapping({ "/User_Main_Home", "/User_SignUp", "User_Login" })
	public void showPage() {

	}
	@Auth(role = Role.CUSTOMER)
	@GetMapping({ "/User_Edit" })
	public void showPageForCustomer() {

	}

	// 아이디 비밀번호 체크
	@PostMapping("/logincheck")
	public String logincheck(@RequestParam("userid") String id, @RequestParam("password") String passwd, HttpSession session) {
		log.info("logincheck:[" + id + "," + passwd + "]");
		int result = service.loginCheck(id, passwd);
		if (result == 0) {
			UserVO user = service.getUser(id);
			session.setAttribute(LoginChecker.SN_LOGIN_USER, user);
			return "redirect:/menu/User_Drink_Menu";
		}
		return "redirect:/user/User_Login?msg=" + result;
	}

	// 로그아웃해서 세션도 없애기
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		log.info("logout..");
		session.invalidate();
		return "redirect:/menu/User_Drink_Menu";
	}

	// 회원가입
	@PostMapping("/signup")
	public String signup(UserDTO user, RedirectAttributes rttr) {
		log.info("signup..");
		service.insertUser(user);
		rttr.addFlashAttribute("result", user.getUserid());
		return "redirect:/menu/User_Drink_Menu";
	}

	// 회원 정보 수정
	@Auth(role = Role.CUSTOMER)
	@PostMapping("/useredit")
	public String useredit(UserDTO user, HttpSession session) {
		// 검색
		log.info(user);
		// model.addAttribute("user",service.CheckId(user.getUserid()));
		// 검색결과 true면 해당 회원 정부 수정
		if (service.modifyUser(user)) {
			log.info(user);
			session.setAttribute(LoginChecker.SN_LOGIN_USER, user);
			return "redirect:/menu/User_Drink_Menu";
		}
		return "redirect:/menu/User_Drink_Menu";
	}

	// 회원 탈퇴
	@Auth(role = Role.CUSTOMER)
	@GetMapping("/userdelete")
	public String userdelete(HttpSession session) {
		UserVO user = (UserVO) session.getAttribute(LoginChecker.SN_LOGIN_USER);
		// 검색결과 true면 해당 회원 탈퇴
		if (service.removeUser(user.getUserid())) {
			return "redirect:/user/logout";
		}
		return "redirect:/menu/User_Drink_Menu";
	}

}
