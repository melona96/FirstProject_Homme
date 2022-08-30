package com.ezen.biz.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.ProductVO;

import utils.Criteria;

@Repository	
public class ProductDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	
	public List<ProductVO> getProductList() {
		return mybatis.selectList("productMapper.getProductList");
	}
	
	public ProductVO getProduct(ProductVO vo) {
		
		return mybatis.selectOne("productMapper.getProduct", vo);
	}
	
	public int getCountProduct() {
		return mybatis.selectOne("productMapper.getCountProduct");
	}
	
	public List<ProductVO> getSearchProduct(String search){
		return mybatis.selectList("productMapper.searchProduct", search);
	}

	public List<ProductVO> getProductListWithPaging(Criteria criteria) {
		
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("criteria", criteria);
		
		return mybatis.selectList("productMapper.getProductListWithPaging", map);
	}
	
	public void insertProduct(ProductVO vo) {
		mybatis.insert("productMapper.insertProduct", vo);
	}
	
	public void updateProduct(ProductVO vo) {
		mybatis.update("productMapper.updateProduct", vo);
	}
	
	public void deleteProduct(int pseq) {
		mybatis.delete("productMapper.deleteProduct", pseq);
	}
}






