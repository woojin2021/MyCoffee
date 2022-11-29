package com.mycoffee.controller;

import java.util.HashMap;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycoffee.common.Auth;
import com.mycoffee.common.Auth.Role;
import com.mycoffee.domain.DriverDTO;
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
	
}
