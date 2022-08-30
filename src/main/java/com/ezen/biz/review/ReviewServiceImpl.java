package com.ezen.biz.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.ReviewDAO;
import com.ezen.biz.dto.ReviewVO;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewDAO rDao;

	@Override
	public int insertReview(ReviewVO vo) {
		
		return rDao.insertComment(vo);
	}

	@Override
	public List<ReviewVO> getReviewList(int pseq) {
		
		return rDao.getReviewList(pseq);
	}

	@Override
	public int countReview(int pseq) {
		
		return rDao.countReview(pseq);
	}
	
	
	
}
