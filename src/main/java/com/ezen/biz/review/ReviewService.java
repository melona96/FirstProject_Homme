package com.ezen.biz.review;

import java.util.List;

import com.ezen.biz.dto.ReviewVO;

import utils.Criteria;


public interface ReviewService {
	int insertReview(ReviewVO vo);
	
	List<ReviewVO> getReviewList(int pseq);
	
	int countReview(int pseq);
}
