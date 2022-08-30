package com.ezen.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.biz.dto.MemberVO;
import com.ezen.biz.dto.ReviewVO;
import com.ezen.biz.review.ReviewService;

@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@RequestMapping(value="/review_write", method=RequestMethod.POST) 
	public String insertReview(ReviewVO vo, HttpSession session, HttpServletRequest request) {
		String Referer = request.getHeader("Referer");
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login"; // 로그인을 해주세요.
		} else { // 정상 작동
			
			vo.setId(loginUser.getId());
			if (reviewService.insertReview(vo) > 0) {
				
				return "redirect: " + Referer;
			} else {
				return "fail";
			}
		}
		
		
	}
	
}
