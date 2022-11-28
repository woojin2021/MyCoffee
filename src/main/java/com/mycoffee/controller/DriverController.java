package com.mycoffee.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycoffee.domain.DriverVO;
import com.mycoffee.common.Auth;
import com.mycoffee.common.Auth.Role;
import com.mycoffee.common.LoginChecker;
import com.mycoffee.domain.DriverDTO;
import com.mycoffee.domain.DriverInfo;
import com.mycoffee.service.DriverService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value = "/driver")
@Log4j
@AllArgsConstructor
public class DriverController {
	
	private DriverService driverservice;

	/* 페이지 이동 */
	@GetMapping({"/login" , "/member/join"})
	public void showPage(HttpServletRequest request) {
		log.info(request.getRequestURI());
	}

	/* 로그인 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginPOST(DriverDTO driver, RedirectAttributes rttr, HttpSession session) {
		DriverVO lvo = driverservice.driverLogin(driver);
		log.info(lvo);
		if (lvo == null) {
			int result = 0;
			rttr.addFlashAttribute("result", result);
			return "redirect: /driver/login";
		}
		
		session.setAttribute(LoginChecker.SN_LOGIN_DRIVER, lvo);
		if (lvo.getAuth() == 1) {
			return "redirect: /driver/manager/driverList";
		}
        return "redirect: /driver/member/driverOrder";
    }
	
	/* 로그아웃 세션 */
	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public String logoutMainGET(HttpSession session) {
		log.info("logout Get method 진입");
		session.invalidate();
		
		return "/driver/login";
	}
	
	// 회원가입
	@RequestMapping(value = "/member/join" , method = RequestMethod.POST)
	public String joinPOST(DriverDTO driver) {
		log.info("join 진입");
		driverservice.memberJoin(driver);
		log.info("join Service 성공");
		
		return "/driver/login";
	}

	@Auth(role = Role.DRIVER)
	@GetMapping(value = "/member/edit")
	public void showDriverEdit(Model model, HttpSession session) {
		model.addAttribute("driver", session.getAttribute(LoginChecker.SN_LOGIN_DRIVER));
	}
	
	// 회원 정보 수정
	@Auth(role = Role.DRIVER)
	@PostMapping(value = "/member/edit")
	public String driverEdit(DriverDTO driver, HttpSession session) {
		log.info(driver);
		if (driverservice.modify(driver)) {
			log.info(driver);
			log.info(session);
			session.setAttribute("did",driver);
			
			return "/driver/member/edit";
		}

		return "/login";
	}
	
	// 아이디 중복 검사
	@RequestMapping(value = "/member/driverIdChk" , method = RequestMethod.POST)
	@ResponseBody
	public String driverIdChkPOST(String did) {
		log.info("driverIdCHk() 진입");
		int result = driverservice.idCheck(did);
		log.info("결과값 = " + result);
		
		if(result != 0) {
			return "fail";	//중복 아이디가 존재
		} else {
			return "success";	//중복 아이디 없음
		}
	}// driverIdChkPOST() 종료
	
	// ----------------------------- 배달원 주문확인 페이지 ----------------------------- //

	@Auth(role = Role.DRIVER)
	@GetMapping(value = "/member/driverOrder")
	public void driverOrder(HttpSession session, Model model) {
		log.info("배달원 주문확인 페이지 진입");
		String did = getDid(session);
		driverservice.getOrder(did, model);
	}

	@Auth(role = Role.DRIVER)
	@PostMapping("/member/driverOrder/checkout")
	public String checkoutOrder(String oid, HttpSession session, RedirectAttributes rttr, Model model) {
		log.info("배달 접수: " + oid);
		String did = getDid(session);
		driverservice.checkoutOrder(oid, did, rttr, model);
		return "/driver/member/driverOrder";
	}

	@Auth(role = Role.DRIVER)
	@PostMapping("/member/driverOrder/complete")
	public String completeOrder(String oid, HttpSession session, RedirectAttributes rttr, Model model) {
		log.info("배달 완료");
		String did = getDid(session);
		driverservice.completeOrder(oid, did, rttr, model);
		return "/driver/member/driverOrder";
	}
	
	// ----------------------------- 배달원 header ----------------------------- //

	@Auth(role = Role.DRIVER)
	@ResponseBody
	@GetMapping(value = "/member/driverOrder/isstarted", produces = {MediaType.APPLICATION_JSON_VALUE})
	//public ResponseEntity<Boolean> workDriver(@RequestParam ("did")String did) {
	public ResponseEntity<Boolean> workDriver(HttpSession session) {
		log.info("sessionid: " + session.getId());
		String did = getDid(session);
		return ResponseEntity.ok(driverservice.workDriver(did));
	}

	@Auth(role = Role.DRIVER)
	@ResponseBody
	@GetMapping(value = "/member/driverOrder/open", produces = {MediaType.APPLICATION_JSON_VALUE})
	//public ResponseEntity<Boolean> startDriver(@RequestParam ("did")String did) {
	public ResponseEntity<Boolean> startDriver(HttpSession session) {
		String did = getDid(session);
		return ResponseEntity.ok(driverservice.startDriver(did));
	}

	@Auth(role = Role.DRIVER)
	@ResponseBody
	@GetMapping(value = "/member/driverOrder/close", produces = {MediaType.APPLICATION_JSON_VALUE})
	//public ResponseEntity<Boolean> endDriver(@RequestParam ("did")String did) {
	public ResponseEntity<Boolean> endDriver(HttpSession session) {
		String did = getDid(session);
		return ResponseEntity.ok(driverservice.endDriver(did));
	}

	@Auth(role = Role.DRIVER)
	@ResponseBody
	@GetMapping(value = "/member/driverOrder/unchecked", produces = {MediaType.APPLICATION_JSON_VALUE})
	//public ResponseEntity<Boolean> endDriver(@RequestParam ("did")String did) {
	public ResponseEntity<List<DriverInfo>> getUncheckedOrder() {
		return ResponseEntity.ok(driverservice.getUncheckedOrder());
	}
	
//	@PostMapping("/driverOrder/status")
//	public String driverStatus(DriverInfo driver) {
//		driverservice.driverStatus(driver);
//		return "redirect: /driver/member/driverOrder";
//	}
	
	private String getDid(HttpSession session) {
		DriverVO driver =  (DriverVO)session.getAttribute(LoginChecker.SN_LOGIN_DRIVER);
		return driver.getDid();
	}
	
}
