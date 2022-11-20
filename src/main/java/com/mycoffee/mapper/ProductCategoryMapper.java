package com.mycoffee.mapper;

import java.util.List;

import com.mycoffee.domain.ProductCategoryDTO;

public interface ProductCategoryMapper {

	public ProductCategoryDTO selectCategory(String pcategory);
	
	public int insertCategory(ProductCategoryDTO category);
	
	public int updateCategory(ProductCategoryDTO category);

	public int deleteCategory(String pcategory);
	
	/**
	 * ptype별 카테고리 리스트String pcategory);
	 * @param ptype 0:음료. 1:음식
	 * @return
	 */
	public List<ProductCategoryDTO> selectCategoryByPtype(int ptype);

}