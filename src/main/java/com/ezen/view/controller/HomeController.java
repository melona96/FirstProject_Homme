package com.ezen.view.controller;

import java.util.List;

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
import com.ezen.biz.dto.ProductVO;
import com.ezen.biz.member.MemberService;
import com.ezen.biz.product.ProductService;

@Controller
@SessionAttributes({"loginUser", "cartList", "cartCnt"})
public class HomeController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CartService cartService;

	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String home(Model model, HttpSession session, MemberVO vo) {
			
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser"); 
		
		if(loginUser == null) {
			
			List<ProductVO> ProductList = productService.getProductList();
			
			model.addAttribute("ProductList", ProductList);
			
		} else {
			
			List<ProductVO> ProductList = productService.getProductList();
			List<CartVO> cartList = cartService.getCartList(loginUser.getId());
			
			int totalAmount = 0;
			for(CartVO cVo : cartList) {
				totalAmount += (cVo.getQuantity() * cVo.getPrice());
			}
			
			int cartCnt = 0;
			cartCnt = cartService.countCart(loginUser.getId());
			
			model.addAttribute("cartCnt", cartCnt);
			model.addAttribute("ProductList", ProductList);
			model.addAttribute("cartList", cartList);
			model.addAttribute("totalPrice", totalAmount);
		}
		
		return "index";
	}
}	









