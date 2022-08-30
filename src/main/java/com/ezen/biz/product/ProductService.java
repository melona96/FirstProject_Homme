package com.ezen.biz.product;

import java.util.List;

import com.ezen.biz.dto.ProductVO;

import utils.Criteria;


public interface ProductService {
	
	List<ProductVO> getProductList();

	ProductVO getProduct(ProductVO vo);
	
	int getCountProduct();
	
	List<ProductVO> searchProduct(String search);
	
	List<ProductVO> getProductListWithPaging(Criteria criteria);
	
	void insertProduct(ProductVO vo); 
	
	void updateProduct(ProductVO vo);
	
	void deleteProduct(int pseq);
}