package com.mycoffee.mapper;

import java.util.List;

import com.mycoffee.domain.ProductCategoryDTO;
import com.mycoffee.domain.ProductCategoryVO;

public interface ProductCategoryMapper {

	public ProductCategoryVO selectCategory(String pcategory);
	
	public int insertCategory(ProductCategoryDTO category);
	
	public int updateCategory(ProductCategoryDTO category);

	public int deleteCategory(String pcategory);
	
	/**
	 * ptype별 카테고리 리스트String pcategory);
	 * @param ptype 0:음료. 1:음식
	 * @return
	 */
	public List<ProductCategoryVO> selectCategoryByPtype(int ptype);

}