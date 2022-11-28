package com.mycoffee.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycoffee.domain.ProductCategoryVO;
import com.mycoffee.service.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
@WebAppConfiguration
@Controller
@Log4j
@RequestMapping("/menu/*")
@AllArgsConstructor
public class ProductController {
	private ProductService service;
	
	@GetMapping("/User_Drink_Menu")
	public void getlist(Integer ptype, Model model)
	{
		log.info("ptype: " + ptype);
		if (ptype == null) {
			ptype =0;
		}
		model.addAttribute("ptype", ptype);
		model.addAttribute("ptypeList", service.getCodeList("PTYPE"));
		model.addAttribute("list", service.getCategoryList(ptype));
	}

	//해당 카테고리에 포함되는 리스트만 가져오기 -상세 설명 페이지/상세 주문 페이지
	@GetMapping({"/User_One_Drink","User_Drink_Order"})
	public void getProductInfo(@RequestParam("category") String category,Model model,HttpServletRequest request)
	{
		model.addAttribute("product",service.getProductList(category));
	}
	
	//메뉴화면
	@GetMapping(value = "/product/list/{ptype}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<ProductCategoryVO>> getProductList(@PathVariable("ptype") Integer ptype) {
		log.info("getProductList");
		return ResponseEntity.ok(service.getCategoryList(ptype));
	}
	
}
