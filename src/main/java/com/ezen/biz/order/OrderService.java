package com.ezen.biz.order;

import java.util.List;

import com.ezen.biz.dto.OrderVO;

import utils.Criteria;

public interface OrderService {

	int getMaxOseq();
	
	int insertOrder(OrderVO vo);
	
	void insertOrderDetail(OrderVO vo);
	
	List<OrderVO> getListOrder(OrderVO vo);

	List<Integer> getOrder(OrderVO vo);

	List<OrderVO> getListOrderDetail(OrderVO vo);

	List<Integer> getSales(Criteria criteria);	
	
	int getCountOrder();
	
}
