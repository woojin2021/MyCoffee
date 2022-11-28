package com.mycoffee.controller;

import java.util.List;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycoffee.common.Auth;
import com.mycoffee.common.LoginChecker;
import com.mycoffee.common.Auth.Role;
import com.mycoffee.domain.OrderrInfo;
import com.mycoffee.domain.ProductCategoryDTO;
import com.mycoffee.domain.ProductCategoryVO;
import com.mycoffee.domain.ProductDTO;
import com.mycoffee.domain.ProductVO;
import com.mycoffee.domain.UserVO;
import com.mycoffee.service.AdminService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin/*")
@AllArgsConstructor
@Log4j
public class AdminController {

	private final int cafeid = 1;

	private AdminService service;

	@GetMapping("/login")
	public void login() {
		log.info("login");
	}

	// --------------------------- 로그인/로그아웃 ---------------------------
	
	@PostMapping("/login")
	public String login(String uid, String pwd, HttpSession session, RedirectAttributes rttr) {
		UserVO user = service.login(uid, pwd); 
		if (user != null) {
			session.setAttribute(LoginChecker.SN_LOGIN_USER, user);
			return "/admin/order/list";
		} else {
			rttr.addFlashAttribute("result", "아이디/비밀번호가 일치하지 않습니다.");
			return "redirect:/admin/login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		log.info("logout");
//		service.logout(session);
		session.invalidate();
		return "/admin/login";
	}

	// --------------------------- 상품 관리 ---------------------------
	
	@Auth(role = Role.STORE_MANAGER)
	@GetMapping("/product/list")
	public void productList(Model model) {
		log.info("productList");
		model.addAttribute("drinkList", service.getCategoryList(0));
		model.addAttribute("foodList", service.getCategoryList(1));
	}

	@Auth(role = Role.STORE_MANAGER)
	@GetMapping("/product/register")
	public void productRegister(Model model) {
		log.info("productRegisterForm");
		model.addAttribute("ptypeList", service.getCodeList("PTYPE"));
	}

	@Auth(role = Role.STORE_MANAGER, type = DispatcherType.ASYNC)
	@GetMapping(value = "/product/check/{pcategory}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ProductCategoryVO> checkPcategory(@PathVariable("pcategory") String pcategory) {
		log.info("checkPcategory");
		return ResponseEntity.ok(service.getCategory(pcategory));
	}

	@Auth(role = Role.STORE_MANAGER)
	@PostMapping("/product/register")
	public String registCategory(ProductCategoryDTO dto, MultipartFile[] uploadfile, RedirectAttributes rttr) {
		log.info("productRegister[" + uploadfile.length + "]");
		
		if (service.addCategory(dto, uploadfile) == 1) {
			rttr.addFlashAttribute("result", "상품 카테고리 정보 등록 완료");
			return "redirect:/admin/product/" + dto.getPcategory();
		} else {
			return "redirect:/admin/product/list";
		}

	}

	@Auth(role = Role.STORE_MANAGER)
	@GetMapping("/product/{pcategory}")
	public String viewCategory(@PathVariable("pcategory") String pcategory, Model model) {
		log.info("product");
		model.addAttribute("category", service.getCategory(pcategory));
		model.addAttribute("productList", service.getProductList(pcategory));
		model.addAttribute("ptypeList", service.getCodeList("PTYPE"));
		model.addAttribute("temperList", service.getCodeList("TEMPER"));
		model.addAttribute("capacityList", service.getCodeList("DRINK"));
		model.addAttribute("onsaleList", service.getCodeList("ONSALE"));
		if (model.getAttribute("category") == null) {
			return "/admin/product/list";
		}
		return "/admin/product/modify";
	}

	@Auth(role = Role.STORE_MANAGER)
	@PostMapping("/product/modify")
	public String modifyCategory(ProductCategoryDTO dto, MultipartFile[] uploadfile, RedirectAttributes rttr) {
		log.info("productModify[" + dto + "]");
		
		if (service.updateCategory(dto, uploadfile) == 1) {
			rttr.addFlashAttribute("result", "카테고리를 수정했습니다.");
			return "redirect:/admin/product/" + dto.getPcategory();
		} else {
			return "redirect:/admin/product/list";
		}

	}

	@Auth(role = Role.STORE_MANAGER)
	@PostMapping("/product/remove")
	public String removeCategory(String pcategory, RedirectAttributes rttr) {
		log.info("removeCategory");
		if (service.removeCategory(pcategory) == 1) {
			rttr.addFlashAttribute("result", "카테고리를 삭제했습니다.");
			return "redirect:/admin/product/list";
		} else {
			rttr.addFlashAttribute("result", "카테고리 삭게에 실패했습니다.");
			return "redirect:/admin/product/" + pcategory;
		}
	}

	@Auth(role = Role.STORE_MANAGER, type = DispatcherType.ASYNC)
	@GetMapping(value = "/product/detail/get/{pid}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ProductVO> getProduct(@PathVariable("pid") String pid) {
		log.info("checkPcategory");
		return ResponseEntity.ok(service.getProduct(pid));
	}

	@Auth(role = Role.STORE_MANAGER)
	@PostMapping("/product/detail/add")
	public String addProduct(ProductDTO dto, RedirectAttributes rttr) {
		if (service.addProduct(dto) == 1) {
			rttr.addFlashAttribute("result", "카테고리에 상품을 추가했습니다.");
		} else {
			rttr.addFlashAttribute("result", "상품 추가에 실패했습니다.");
		}
		return "redirect:/admin/product/" + dto.getPcategory();
	}

	@Auth(role = Role.STORE_MANAGER)
	@PostMapping("/product/detail/modify")
	public String modifyProduct(ProductDTO dto, RedirectAttributes rttr) {
		
		if (service.updateProduct(dto) == 1) {
			rttr.addFlashAttribute("result", "상품을 수정했습니다.");
		} else {
			rttr.addFlashAttribute("result", "상품 수정에 실패했습니다.");
		}
		return "redirect:/admin/product/" + dto.getPcategory();
	}

	@Auth(role = Role.STORE_MANAGER)
	@PostMapping("/product/detail/remove")
	public String removeProduct(ProductDTO dto, RedirectAttributes rttr) {
		if (service.removeProduct(dto.getPid()) == 1) {
			rttr.addFlashAttribute("result", "상품을 삭제했습니다.");
		} else {
			rttr.addFlashAttribute("result", "상품 삭제에 실패했습니다.");
		}
		return "redirect:/admin/product/" + dto.getPcategory();
	}

	// --------------------------- 헤더 정보 ---------------------------
	
	@Auth(role = Role.STORE_MANAGER)
	@GetMapping("/order/list")
	public void orderList() {
		log.info("orderList");
//		model.addAttribute("orderList", service.getOrderList());
	}
	
	@Auth(role = Role.STORE_MANAGER, type = DispatcherType.ASYNC)
	@GetMapping(value = "/cafe/isopened", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Boolean> isCafeOpened() {
		return ResponseEntity.ok(service.isCafeOpened(cafeid));
	}

	@Auth(role = Role.STORE_MANAGER, type = DispatcherType.ASYNC)
	@GetMapping(value = "/cafe/open", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Boolean> openCafe() {
		return ResponseEntity.ok(service.openCafe(cafeid));
	}

	@Auth(role = Role.STORE_MANAGER, type = DispatcherType.ASYNC)
	@GetMapping(value = "/cafe/close", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Boolean> closeCafe() {
		return ResponseEntity.ok(service.closeCafe(cafeid));
	}

	@Auth(role = Role.STORE_MANAGER, type = DispatcherType.ASYNC)
	@GetMapping(value = "/driver/free", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Integer> getFreeDriverCount() {
		return ResponseEntity.ok(service.getFreeDriverCount());
	}

	@Auth(role = Role.STORE_MANAGER, type = DispatcherType.ASYNC)
	@GetMapping(value = "/order/waiting", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Integer> getWaitingOrderCount() {
		return ResponseEntity.ok(service.getWaitingOrderCount());
	}

	// --------------------------- 주문 목록 ---------------------------
	
	@Auth(role = Role.STORE_MANAGER, type = DispatcherType.ASYNC)
	@GetMapping(value = "/order/list/data", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<OrderrInfo>> getOrderListData() {
		return ResponseEntity.ok(service.getOrderList());
	}

	@Auth(role = Role.STORE_MANAGER, type = DispatcherType.ASYNC)
	@GetMapping(value = "/order/list/{oper}/{oid}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<OrderrInfo>> updateOrderData(@PathVariable("oper") String oper, @PathVariable("oid") String oid) {
		log.info("operation: " + oper + " oid: " + oid);
		return ResponseEntity.ok(service.modifyOrderStatus(oper, oid));
	}

	/*
	 * Filter에서 	
	 * DispatcherType.ASYNC 관련 자료
	 * https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-async
	 * https://www.baeldung.com/spring-deferred-result
	 */
	@Auth(role = Role.STORE_MANAGER, type = DispatcherType.ASYNC)
	@GetMapping(value = "/test", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public DeferredResult<String> asyncTest() {
		log.info("asyncTest");
		DeferredResult<String> deferredResult = new DeferredResult<>();
		deferredResult.setResult("asyncTest success!");
		return deferredResult;
	}

}
