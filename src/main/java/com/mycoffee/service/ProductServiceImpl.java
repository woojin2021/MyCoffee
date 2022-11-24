package com.mycoffee.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mycoffee.domain.CodesVO;
import com.mycoffee.domain.ProductJoinVO;
import com.mycoffee.domain.ProductViewVO;
import com.mycoffee.domain.ProductCategoryVO;
import com.mycoffee.domain.ProductInfo;
import com.mycoffee.mapper.CodesMapper;
import com.mycoffee.mapper.ProductViewMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{
	
	private ProductViewMapper mapper;
	private CodesMapper codesMapper;
	
	@Override
	public ProductCategoryVO getCategory(String category) {
		log.info(category);
//		log.info(mapper.selectCategory(category));
		return mapper.selectCategory(category);
		
	}

	@Override
	public List<ProductCategoryVO> getCategoryList(int ptype) {
		log.info("get........");
		return mapper.selectCategoryList(ptype);
	}

//	@Override
//	public int getcount() {
//		log.info("get........");
//		int i =mapper.getcount();
//		return i;
//	}

	/*
	 * @Override public List<Product_CategoryVO> getlist1(String category) { return
	 * mapper.getlist1(category); }
	 */
	@Override
	public ProductInfo getProductList(String category) {
		//log.info(mapper.getlist1(category));
		ProductCategoryVO cate = mapper.selectCategory(category);
		List<ProductViewVO> products = mapper.selectProductList(category);
		Gson json = new Gson();
		
		ProductInfo pInfo = new ProductInfo(
				cate.getPcategory(), 
				cate.getPname(), 
				cate.getPtype(), 
				cate.getDescription(), 
				cate.getCalorie(), 
				cate.getFat(), 
				cate.getSugars(), 
				cate.getSodium(), 
				cate.getCaffeine(), 
				cate.getProtein(),
				cate.getImagefile(),
				json.toJson(products));
		return pInfo;
	}

	@Override
	public List<CodesVO> getCodeList(String type) {
		log.info("getCodeList: [type=" + type + "]");
		return codesMapper.getCodeList(type);
	}
	
	//추가된 것
	@Override
	public ProductJoinVO getProductByPK(String category, int tem, int cap) {
		//log.info(mapper.get2(category, tem, cap));
		return mapper.selectProductByPK(category, tem, cap);
	}

	@Override
	public ProductJoinVO getProductByPid(String pid) {
		return mapper.selectProductByPid(pid);
	}

}
