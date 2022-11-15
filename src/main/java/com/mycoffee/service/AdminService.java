package com.mycoffee.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.mycoffee.domain.CodesVO;
import com.mycoffee.domain.OrderrInfo;
import com.mycoffee.domain.ProductCategoryDTO;
import com.mycoffee.domain.ProductDTO;

public interface AdminService {

	public List<CodesVO> getCodeList(String type);
	
	public List<ProductCategoryDTO> getCategoryList(int ptype);

	public List<ProductDTO> getProductList(String pcategory);
	
	public ProductCategoryDTO getCategory(String pcategory);
	
	public int addCategory(ProductCategoryDTO dto, MultipartFile[] uploadfile);

	public int updateCategory(ProductCategoryDTO dto, MultipartFile[] uploadfile);

	public int removeCategory(String pcategory);

	public ProductDTO getProduct(String pid);
	
	public int addProduct(ProductDTO dto);

	public int updateProduct(ProductDTO dto);

	public int removeProduct(String pid);
	
	public boolean isCafeOpened(int cafeid);
	
	public boolean openCafe(int cafeid);
	
	public boolean closeCafe(int cafeid);

	public int getFreeDriverCount();
	
	public int getWaitingOrderCount();
	
	public List<OrderrInfo> getOrderList();
	
	public List<OrderrInfo> modifyOrderStatus(String oper, String oid);

	public boolean login(String uid, String passwrod, HttpSession session);
	
	public void logout(HttpSession session);
}
