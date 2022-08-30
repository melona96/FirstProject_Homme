package com.ezen.biz.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.cart.CartService;
import com.ezen.biz.dao.OrderDAO;
import com.ezen.biz.dto.CartVO;
import com.ezen.biz.dto.OrderVO;

import utils.Criteria;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDAO oDao;
	
	@Autowired
	CartService cartService;
	
	
	@Override
	public int getMaxOseq() {
		return oDao.selectMaxOseq();
	}

	@Override
	public int insertOrder(OrderVO vo) {
		int oseq = getMaxOseq();
		
		vo.setOseq(oseq);
		oDao.insertOrder(vo);
		
		List<CartVO> cartList = cartService.getCartList(vo.getId());
		
		for(CartVO cart : cartList) {
			OrderVO order = new OrderVO();
			
			order.setOseq(oseq);
			order.setPseq(cart.getPseq());
			order.setQuantity(cart.getQuantity());
			order.setPsize(cart.getPsize());
			
			insertOrderDetail(order);
			
			cartService.updateCart(cart.getCseq());
		}
		
		return oseq;
	}

	@Override
	public void insertOrderDetail(OrderVO vo) {
		oDao.insertOrderDetail(vo);
	}

	@Override
	public List<OrderVO> getListOrder(OrderVO vo) {
		
		return oDao.getListOrder(vo);
	}

	@Override
	public List<Integer> getOrder(OrderVO vo) {
		
		return oDao.getOrder(vo);
	}

	@Override
	public List<OrderVO> getListOrderDetail(OrderVO vo) {
		return oDao.getListOrderDetail(vo);
	}


	@Override
	public List<Integer> getSales(Criteria criteria) {
		return oDao.getSales(criteria);
	}

	@Override
	public int getCountOrder() {
		return oDao.countOrder();
	}

	


	
}
