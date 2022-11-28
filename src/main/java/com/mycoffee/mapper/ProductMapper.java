package com.mycoffee.mapper;

import java.util.List;

import com.mycoffee.domain.ProductDTO;
import com.mycoffee.domain.ProductVO;

public interface ProductMapper {
	
	public ProductVO selectProduct(String pid);

	public int insertProduct(ProductDTO category);

	public int updateProduct(ProductDTO category);

	public int deleteProduct(String pid);

	/**
	 * pcategory에 속한 상품 리스트
	 * @param pcategory
	 * @return
	 */
	public List<ProductVO> selectProductInCategory(String pcategory);

	/**
	 * pcategory에 속한 상품 일괄 삭제
	 * @param pcategory
	 * @return
	 */
	public int deleteProductInCategory(String pcategory);
	
}
