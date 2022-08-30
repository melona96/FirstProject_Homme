package com.ezen.view.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ezen.biz.cart.CartService;
import com.ezen.biz.dto.CartVO;
import com.ezen.biz.dto.MemberVO;
import com.ezen.biz.member.MemberService;

@Controller
@SessionAttributes({"loginUser"})
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CartService cartService;
	
	
	// 로그인 화면 이동
	@RequestMapping(value="/login_form", method=RequestMethod.GET)
	public String loginView() {
		return "member/login";
	}
	
	
	// 로그인 처리
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginAction(MemberVO vo, Model model, HttpSession session) {
		MemberVO user = null;
		
		
		int result = memberService.loginID(vo);
		
		if (result == 1) {  // 로그인 성공
			user = memberService.getMember(vo.getId());
			model.addAttribute("loginUser", user);
			
			/*
			List<CartVO> cartList = cartService.getCartList(vo.getId());
			model.addAttribute("cartList", cartList);
			*/
			
			return "redirect:index"; 
		} else {
			return "member/login_fail";
		}
	}
	
	
	
	
	// 회원가입 화면 이동
	@RequestMapping(value="/join_form", method=RequestMethod.GET)
	public String joinView() {
		return "member/join";
	}
	
	// 회원가입 처리
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinAction(@RequestParam(value="jibunAddress") String jibunAddress,
							 @RequestParam(value="detailAddress") String detailAddress,
							 MemberVO vo) {
		
		vo.setAddress(jibunAddress + " " + detailAddress);
		memberService.insertMember(vo);
		
		return "/index";
	}
	
	
	// 로그아웃 처리
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(SessionStatus status, HttpSession session) {
		
		status.setComplete();
		session.invalidate();
		
		return "redirect:index";
	}
	
	// 회원탈퇴 처리
	@RequestMapping(value="/member_delete", method=RequestMethod.GET)
	public String deleteMemberAction(SessionStatus status, HttpSession session) {
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		String id = loginUser.getId();
		
		memberService.deleteMember(id);
		
		status.setComplete();
		
		return "redirect:index";
	}
	
	@RequestMapping(value="/member_delete_admin", method=RequestMethod.GET)
	public String deleteMemberAction2(SessionStatus status, HttpSession session, MemberVO vo, HttpServletRequest request) {
		String Referer = request.getHeader("Referer");
		String id = vo.getId();
		
		memberService.deleteMember(id);
		
		status.setComplete();
		
		return "redirect: " + Referer;
	}
	
	@RequestMapping(value="/change_pwd_form", method=RequestMethod.GET)
	public String changePwdView() {
		return "mypage/changePwd";
	}
	
	@RequestMapping(value="/change_pwd", method=RequestMethod.POST)
	public String changePwdAction(HttpSession session, MemberVO vo, SessionStatus status) {
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if(loginUser == null) {
			return "member/login";
		} else {
			
			vo.setId(loginUser.getId());
			 
			memberService.changePwd(vo);
			
			status.setComplete();
			session.invalidate();
			
			return "index";
		}
	}	
}










