package com.ezen.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ezen.biz.cart.CartService;
import com.ezen.biz.dto.MemberVO;
import com.ezen.biz.dto.OrderVO;
import com.ezen.biz.order.OrderService;

@Controller
@SessionAttributes({"cartCnt"})
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping(value="/insert_order", method=RequestMethod.POST)
	public String insertOrderAction(HttpSession session, OrderVO vo, Model model, HttpServletRequest request) {
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		String Referer = request.getHeader("Referer");
		
		vo.setId(loginUser.getId());
		
		if(vo.getQuantity() == 0) {
			return "cart/order_fail";
		} else {
			
			int oseq = orderService.insertOrder(vo);
		
			model.addAttribute("oseq", oseq);
			
			int cartCnt = 0;
			cartCnt = cartService.countCart(loginUser.getId());
			
			model.addAttribute("cartCnt", cartCnt);
		
			return "redirect: " + Referer;
		}
	}
}
