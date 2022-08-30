package com.ezen.view.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ezen.biz.cart.CartService;
import com.ezen.biz.dto.CartVO;
import com.ezen.biz.dto.MemberVO;

@Controller
@SessionAttributes({"cartList", "cartCnt"})
public class CartController {

	@Autowired
	CartService cartService;
	
	@RequestMapping(value="/insert_cart", method=RequestMethod.POST)
	public String insertCartAction(CartVO vo, HttpSession session, HttpServletRequest request, Model model) {
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		String Referer = request.getHeader("Referer");
		
		if (loginUser == null) {
			
			return "member/login";
		} else {
			
			vo.setId(loginUser.getId());
			
			cartService.insertCart(vo);
			
			List<CartVO> cartList = cartService.getCartList(vo.getId());
			
			model.addAttribute("cartList", cartList);
			
			int cartCnt = 0;
			cartCnt = cartService.countCart(loginUser.getId());
			
			model.addAttribute("cartCnt", cartCnt);
			
			// 장바구니 목록 확인
			return "redirect: productList";
		}
	}
	
	@RequestMapping(value="/cart_list", method=RequestMethod.GET)
	public String getCartList(HttpSession session, Model model) {
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser"); 
		
		if(loginUser == null) {
			return "member/login";
		} else {
			List<CartVO> cartList = cartService.getCartList(loginUser.getId());
			
			int totalAmount = 0;
			for(CartVO vo : cartList) {
				totalAmount += (vo.getQuantity() * vo.getPrice());
			}
			
			model.addAttribute("cartList", cartList);
			model.addAttribute("totalPrice", totalAmount);
		
			return "cart/cart";
		}
	}
	
	@RequestMapping(value="/delete_cart_list", method=RequestMethod.GET)
	public String deleteCartList(CartVO vo, HttpSession session, HttpServletRequest request, Model model) {
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		String Referer = request.getHeader("Referer");;
		
		String id = loginUser.getId();
		
		cartService.deleteCartList(id);
		
		int cartCnt = 0;
		cartCnt = cartService.countCart(loginUser.getId());
		
		model.addAttribute("cartCnt", cartCnt);
		
		return "redirect: " + Referer;
		
	}
}
