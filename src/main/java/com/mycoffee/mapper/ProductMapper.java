package com.mycoffee.mapper;

import java.util.List;

import com.mycoffee.domain.ProductDTO;

public interface ProductMapper {
	
	public ProductDTO selectProduct(String pid);

	public int insertProduct(ProductDTO category);

	public int updateProduct(ProductDTO category);

	public int deleteProduct(String pid);

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
	
}
