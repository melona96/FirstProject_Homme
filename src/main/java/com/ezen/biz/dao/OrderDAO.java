package com.ezen.biz.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.OrderVO;

import utils.Criteria;

@Repository
public class OrderDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int selectMaxOseq() {
		return mybatis.selectOne("orderMapper.selectMaxOseq");
	}
	
	public void insertOrder(OrderVO vo) {
		mybatis.insert("orderMapper.insertOrder", vo);
	}
	
	public void insertOrderDetail(OrderVO vo) {
		mybatis.insert("orderMapper.insertOrderDetail", vo);
	}
	
	public List<OrderVO> getListOrder(OrderVO vo) {
		return mybatis.selectList("orderMapper.getListOrder", vo);
	}
	
	public List<Integer> getOrder(OrderVO vo) {
		return mybatis.selectList("orderMapper.selectOrder", vo);
	}
	
	public List<OrderVO> getListOrderDetail(OrderVO vo) {
		return mybatis.selectList("orderMapper.getListOrderDetail", vo);
	}
	
	public List<Integer> getSales(Criteria criteria) {
		
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("criteria", criteria);
		
		return mybatis.selectList("orderMapper.selectSales", map);
	}
	
	public int countOrder() {
		return mybatis.selectOne("orderMapper.countOrder");
	}
	
}
