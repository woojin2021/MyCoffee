package com.mycoffee.mapper;

import java.util.List;

import com.mycoffee.domain.ProductCategoryDTO;
import com.mycoffee.domain.ProductDTO;

public interface ProductInfoMapper {
	
	
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
	
	/**
	 * pcategory에 속한 상품 리스트
	 * @param pcategory
	 * @return
	 */
	public List<ProductDTO> selectProductInCategory(String pcategory);

	/**
	 * pcategory에 속한 상품 일괄 삭제
	 * @param pcategory
	 * @return
	 */
	public int deleteProductInCategory(String pcategory);
	
	public ProductDTO selectProduct(String pid);

	public int insertProduct(ProductDTO category);
	
	public int updateProduct(ProductDTO category);

	public int deleteProduct(String pid);
	

}
