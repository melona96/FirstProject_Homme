package com.ezen.biz.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.CartDAO;
import com.ezen.biz.dto.CartVO;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	CartDAO cDao;
	
	@Override
	public void insertCart(CartVO vo) {
		cDao.insertCart(vo);
		
	}

	@Override
	public void deleteCart(int cseq) {
		cDao.deleteCart(cseq);
		
	}

	@Override
	public List<CartVO> getCartList(String id) {
		return cDao.getCartList(id);
	}

	@Override
	public void updateCart(int cseq) {
		cDao.updateCart(cseq);
		
	}

	@Override
	public int countCart(String id) {
		return cDao.countCart(id);
	}

	@Override
	public void deleteCartList(String id) {
		cDao.deleteCartList(id);
		
	}
	
	
}
