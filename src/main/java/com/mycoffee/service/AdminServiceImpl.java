package com.mycoffee.service;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mycoffee.common.FileUploader;
import com.mycoffee.domain.CafeDTO;
import com.mycoffee.domain.CodesVO;
import com.mycoffee.domain.OrderOfTodyVO;
import com.mycoffee.domain.OrderrInfo;
import com.mycoffee.domain.ProductCategoryDTO;
import com.mycoffee.domain.ProductCategoryVO;
import com.mycoffee.domain.ProductDTO;
import com.mycoffee.domain.ProductVO;
import com.mycoffee.domain.UserVO;
import com.mycoffee.mapper.CafeMapper;
import com.mycoffee.mapper.CodesMapper;
import com.mycoffee.mapper.OrderViewMapper;
import com.mycoffee.mapper.ProductCategoryMapper;
import com.mycoffee.mapper.ProductMapper;
import com.mycoffee.mapper.UserMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class AdminServiceImpl implements AdminService {

	private ServletContext servletContext;
	
	private ProductCategoryMapper categoryMapper;
	private ProductMapper productMapper;
	private CodesMapper codesMapper;
	private CafeMapper cafeMapper;
	private OrderViewMapper orderMapper;
	private UserMapper userMapper;
	
	@Override
	public List<CodesVO> getCodeList(String type) {
		log.info("getCodeList: [type=" + type + "]");
		return codesMapper.getCodeList(type);
	}
	
	@Override
	public List<ProductCategoryVO> getCategoryList(int ptype) {
		log.info("getCategoryList: [ptype=" + ptype + "]");		
		return categoryMapper.selectCategoryByPtype(ptype);
	}

	@Override
	public List<ProductVO> getProductList(String pcategory) {
		log.info("getProductList: [pcategory=" + pcategory + "]");
		return productMapper.selectProductInCategory(pcategory);
	}

	@Override
	public ProductCategoryVO getCategory(String pcategory) {
		log.info("getCategory: [pcategory=" + pcategory + "]");		
		return categoryMapper.selectCategory(pcategory);
	}

	@Override
	public int addCategory(ProductCategoryDTO dto, MultipartFile[] uploadfile) {
		log.info("addCategory");

		// 파일 업로드
		String uploadFolder = getUploadFolder();
		FileUploader uploader = new FileUploader(uploadFolder);
		if (uploader.saveFile(uploadfile[0])) {
			dto.setImagefile(uploader.getRealFileName());
		}
		
		// enctype="multipart/form-data"로 인한 강제 charset 변경 대응
		try {
			dto.setPname(new String(dto.getPname().getBytes("8859_1"), "UTF-8"));
			dto.setDescription(new String(dto.getDescription().getBytes("8859_1"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return categoryMapper.insertCategory(dto);
	}

	@Override
	public int updateCategory(ProductCategoryDTO dto, MultipartFile[] uploadfile) {
		log.info("updateCategory");

		// enctype="multipart/form-data"로 인한 강제 charset 변경 대응
		try {
			dto.setPname(new String(dto.getPname().getBytes("8859_1"), "UTF-8"));
			dto.setDescription(new String(dto.getDescription().getBytes("8859_1"), "UTF-8"));
			dto.setImagefile(new String(dto.getImagefile().getBytes("8859_1"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// 파일 업로드
		String oldImageFile = null;
		String uploadFolder = getUploadFolder();
		FileUploader uploader = new FileUploader(uploadFolder);
		if (uploadfile.length > 0 && uploader.saveFile(uploadfile[0])) {
			oldImageFile = dto.getImagefile();
			dto.setImagefile(uploader.getRealFileName());
		}
		
		int result = categoryMapper.updateCategory(dto);
		if (result == 1) {
			if (oldImageFile != null && !oldImageFile.isEmpty()) {
				log.info("deleteOldFile:" + oldImageFile);
				uploader.deleteOldFile(oldImageFile);
			}
		}
		
		return result;
	}

	@Override
	public int removeCategory(String pcategory) {
		log.info("removeCategory: [pcategory=" + pcategory + "]");
		productMapper.deleteProductInCategory(pcategory);
		return  categoryMapper.deleteCategory(pcategory);
	}

	@Override
	public ProductVO getProduct(String pid) {
		log.info("getProduct: [pid=" + pid + "]");
		return productMapper.selectProduct(pid);
	}

	@Override
	public int addProduct(ProductDTO dto) {
		log.info("addProduct");
		return productMapper.insertProduct(dto);
	}

	@Override
	public int updateProduct(ProductDTO dto) {
		log.info("updateProduct");
		return productMapper.updateProduct(dto);
	}

	@Override
	public int removeProduct(String pid) {
		log.info("removeProduct: [pid=" + pid + "]");
		return productMapper.deleteProduct(pid);
	}

	@Override
	public boolean isCafeOpened(int cafeid) {
		CafeDTO cafe = cafeMapper.selectCafe(cafeid);
		return (cafe.getCafeopen() == 1);
	}

	@Override
	public boolean openCafe(int cafeid) {
		log.info("openCafe");
		CafeDTO dto = new CafeDTO();
		dto.setCafeid(cafeid);
		dto.setCafeopen(1);
		cafeMapper.updateCafeOpen(dto);
		return isCafeOpened(cafeid);
	}

	@Override
	public boolean closeCafe(int cafeid) {
		log.info("closeCafe");
		CafeDTO dto = new CafeDTO();
		dto.setCafeid(cafeid);
		dto.setCafeopen(0);
		cafeMapper.updateCafeOpen(dto);
		return isCafeOpened(cafeid);
	}

	@Override
	public int getFreeDriverCount() {
		return cafeMapper.getFreeDriverCount();
	}

	@Override
	public int getWaitingOrderCount() {
		return cafeMapper.getWaitingOrderCount();
	}
	
	private String getUploadFolder() {
		return servletContext.getRealPath("/resources/img");
	}

	@Override
	public List<OrderrInfo> getOrderList() {
		List<OrderrInfo> oList = new ArrayList<>();
		
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		String oid = "";
		OrderrInfo temp = null;
		for (OrderOfTodyVO data : orderMapper.getRawOrderList()) {
			if (oid.equals(data.getOid()) == false) {
				temp = new OrderrInfo();
				temp.setOid(data.getOid());
				temp.setOrdertime(formatter.format(data.getOrderdate()));
				temp.setStatus(data.getStatus());
				temp.setStatusdisp(data.getStatusdisp());
				temp.setOrderdetail(getProductSummary(data));
				temp.setTotalprice(data.getTotalprice());
				temp.setUserinfo(data.getName().substring(0, 1) + "OO<br>" + data.getMobile());
				oList.add(temp);
				oid = data.getOid();
			} else {
				temp.setOrderdetail(temp.getOrderdetail() + "<br>" + getProductSummary(data));
			}
			
		}
		return oList;
	}

	// 【 수량 】 상품명 (소계 원)
	private String getProductSummary(OrderOfTodyVO data) {
		DecimalFormat decFormat = new DecimalFormat("#,###");
		String summary = "【 "; //【 2 】카라멜 마키아또 (3,500 원)
		summary += data.getPieces() + " 】 ";
		if (data.getPtype() == 0) {
			summary += data.getTemperaturedisp() + "_";
		}
		summary += data.getPname() + " (" + decFormat.format(data.getPieces() * data.getPrice()) + "원)";
		return summary;
	}

	@Override
	public List<OrderrInfo> modifyOrderStatus(String oper, String oid) {
		int newStatus = -1;
		if (oper.equals("cancel")) {
			newStatus = 6; // 주문 취소
		} else if (oper.equals("accept")) {
			newStatus = 2; // 주문 접수
		} else if (oper.equals("deliver")) {
			newStatus = 3; // 배달 의뢰
		}
		if (newStatus > 0)
			orderMapper.updateOrderStatus(oid, newStatus);
		
		return getOrderList();
	}

	@Override
	public UserVO login(String uid, String passwrod) {
		UserVO user = userMapper.selectUser(uid);
		if (user == null || user.getPassword().equals(passwrod) == false || user.getAuth() != 1) {
			return null;
		}
		return user;
	}

//	@Override
//	public void logout(HttpSession session) {
//		session.removeAttribute(LoginChecker.SN_AUTH);
//	}
	
}