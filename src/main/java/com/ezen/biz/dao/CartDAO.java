package com.ezen.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.CartVO;

@Repository
public class CartDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertCart(CartVO vo) {
		mybatis.insert("cartMapper.insertCart", vo);
	}
	
	public void deleteCart(int cseq) {
		mybatis.delete("cartMapper.deleteCart", cseq);
	}
	
	public List<CartVO> getCartList(String id) {
		return mybatis.selectList("cartMapper.getCartList", id);
	}
	
	public void updateCart(int cseq) {
		mybatis.update("cartMapper.updateCart", cseq);
	}
	
	public int countCart(String id) {
		return mybatis.selectOne("cartMapper.getCartCount", id);
	}
	
	public void deleteCartList(String id) {
		mybatis.delete("cartMapper.deleteCartList", id);
	}
}
