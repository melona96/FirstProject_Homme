package com.ezen.biz.cart;

import java.util.List;

import com.ezen.biz.dto.CartVO;

public interface CartService {
	
	void insertCart(CartVO vo);
	
	void deleteCart(int cseq);
	
	List<CartVO> getCartList(String id);
	
	void updateCart(int cseq);
	
	int countCart(String id);
	
	void deleteCartList(String id);
	
	
}
