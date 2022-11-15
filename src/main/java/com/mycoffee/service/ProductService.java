package com.mycoffee.service;

import java.util.List;

import com.mycoffee.domain.CodesVO;
import com.mycoffee.domain.ProductJoinVO;
import com.mycoffee.domain.Product_CategoryVO;
public interface ProductService {
	public Product_CategoryVO get(String category);
	public List<Product_CategoryVO> getlist(int ptype);
	//public List<Product_CategoryVO> getlist1(String category);
	public List<ProductJoinVO> getlist1(String category);
	public int getcount();
	public List<CodesVO> getCodeList(String type);
	
	//추가된것
	public ProductJoinVO get2(String category, int tem, int cap);
	public ProductJoinVO get3(String pid);
}
