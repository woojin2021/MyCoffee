package com.mycoffee.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.mycoffee.common.Auth;
import com.mycoffee.common.Auth.Role;
import com.mycoffee.domain.DriverDTO;
import com.mycoffee.domain.DriverSummaryVO;
import com.mycoffee.domain.DriverVO;
import com.mycoffee.service.DriverManager;
import com.mycoffee.service.DriverService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value = "/driver")
@Log4j
@AllArgsConstructor
public class DriverManagerController {

	private DriverManager managerservice;
	private DriverService driverservice;

	/* 배달원 현황 조회 */
	@Auth(role = Role.DRIVER_MANAGER)
	@GetMapping("/manager/driverList")
	public void list(Model model){
		log.info("배달원현황 관리자 페이지 진입");
		model.addAttribute("list", managerservice.getDriverSummaryList());
	}

	@Auth(role = Role.DRIVER_MANAGER)
	@PostMapping(value = "/member/list/data", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<DriverVO> getDriver(@RequestBody HashMap<String, Object> param){
		log.info("param:" + param);
		String did = (String)param.get("did");
		return ResponseEntity.ok(driverservice.getDriver(did));
	}

	@Auth(role = Role.DRIVER_MANAGER)
	@PostMapping("/manager/join/approve")
	public String approveRegist(DriverDTO driver) {
		managerservice.approveRegist(driver);
		return "redirect: /driver/manager/driverList";
	}

	@Auth(role = Role.DRIVER_MANAGER)
	@PostMapping("/manager/join/reject")
	public String rejectRegist(DriverDTO driver){
		managerservice.rejectRegist(driver);
		return "redirect: /driver/manager/driverList";
	}

	// Android용 함수
	
	@RequestMapping(value = "/manager/token", method = {RequestMethod.POST},  produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> getToken(@RequestBody DriverDTO driver) {
		log.info("안드로이드 연동: 로그인 [" + driver + "]");
		DriverVO lvo = driverservice.driverLogin(driver);
		String token = "";
		if (lvo != null && lvo.getAuth() == 1) {
			token = driverservice.getToken(driver);
		}
		return ResponseEntity.ok(token);
	}

	@RequestMapping(value = "/manager/driverList2", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<DriverSummaryVO>> getDriverList(String token){
		log.info("안드로이드 연동: 배달원 리스트");
		if (driverservice.isTokenExpired(token)) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "invalid token");
		}
		return ResponseEntity.ok(managerservice.getDriverSummaryList());
	}

	@RequestMapping(value = "/manager/driverData2", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<DriverVO> getDriver(String token, String did){
		log.info("안드로이드 연동: 배달원 정보");
		if (driverservice.isTokenExpired(token)) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "invalid token");
		}
		return ResponseEntity.ok(driverservice.getDriver(did));
	}

	@RequestMapping(value = "/manager/join/approve2", method = {RequestMethod.PUT})
	@ResponseBody
	public String approveRegist2(@RequestBody DriverDTO driver, @RequestParam("token") String token) {
		log.info("안드로이드 연동: 배달원 승인 [" + driver + "]");
		log.info("안드로이드 연동: 배달원 승인 [" + token + "]");
//		managerservice.approveRegist(driver);
		return "redirect: /driver/manager/driverList";
	}

	@GetMapping("/manager/join/reject2")
	@ResponseBody
	public ResponseEntity<String> rejectRegist2(String did){
		log.info("안드로이드 연동: 배달원 거부");
		DriverDTO driver = new DriverDTO();
		driver.setDid(did);
//		managerservice.rejectRegist(driver);
		return ResponseEntity.ok("success");
	}

}
