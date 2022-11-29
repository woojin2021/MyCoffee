package com.mycoffee.service;

import java.util.List;

import com.mycoffee.domain.CodesVO;
import com.mycoffee.domain.ProductJoinVO;
import com.mycoffee.domain.ProductCategoryVO;
import com.mycoffee.domain.ProductInfo;
public interface ProductService {
	
	public ProductCategoryVO getCategory(String category);
	
	public List<ProductCategoryVO> getCategoryList(int ptype);
	
	//public List<Product_CategoryVO> getlist1(String category);
	
	public ProductInfo getProductList(String category);
	
//	public int getcount();
	
	public List<CodesVO> getCodeList(String type);
	
	//추가된것
	public ProductJoinVO getProductByPK(String category, int tem, int cap);
	
	public ProductJoinVO getProductByPid(String pid);
}
