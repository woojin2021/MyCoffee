package com.mycoffee.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mycoffee.domain.CodesVO;
import com.mycoffee.domain.OrderrInfo;
import com.mycoffee.domain.ProductCategoryDTO;
import com.mycoffee.domain.ProductCategoryVO;
import com.mycoffee.domain.ProductDTO;
import com.mycoffee.domain.ProductVO;
import com.mycoffee.domain.UserVO;

public interface AdminService {

	public List<CodesVO> getCodeList(String type);
	
	public List<ProductCategoryVO> getCategoryList(int ptype);

	public List<ProductVO> getProductList(String pcategory);
	
	public ProductCategoryVO getCategory(String pcategory);
	
	public int addCategory(ProductCategoryDTO dto, MultipartFile[] uploadfile);

	public int updateCategory(ProductCategoryDTO dto, MultipartFile[] uploadfile);

	public int removeCategory(String pcategory);

	public ProductVO getProduct(String pid);
	
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

	public UserVO login(String uid, String passwrod);
	
//	public void logout(HttpSession session);
}
