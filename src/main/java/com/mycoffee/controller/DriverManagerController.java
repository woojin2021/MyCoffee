package com.mycoffee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycoffee.domain.DriverDTO;
import com.mycoffee.service.DriverManager;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value = "/driver")
@Log4j
@AllArgsConstructor
public class DriverManagerController {

	private DriverManager managerservice;

	/* 배달원 현황 조회 */
	@GetMapping("/manager/driverList")
	public void list(Model model){
		log.info("배달원현황 관리자 페이지 진입");
		model.addAttribute("list", managerservice.getDriverSummaryList());
	}

	@PostMapping("/manager/join/approve")
	public String approveRegist(DriverDTO driver) {
		managerservice.approveRegist(driver);
		return "redirect: /driver/manager/driverList";
	}

	@PostMapping("/manager/join/reject")
	public String rejectRegist(DriverDTO driver){
		managerservice.rejectRegist(driver);
		return "redirect: /driver/manager/driverList";
	}
	
}
