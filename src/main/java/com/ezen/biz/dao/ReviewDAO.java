package com.ezen.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.ReviewVO;

@Repository
public class ReviewDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int insertComment(ReviewVO vo) {
		return mybatis.insert("reviewMapper.insertReview", vo);
	}
	
	public List<ReviewVO> getReviewList(int pseq) {
		return mybatis.selectList("reviewMapper.getReviewList", pseq);
	}
	
	public int countReview(int pseq) {
		return mybatis.selectOne("reviewMapper.countReview", pseq);
	}
}
