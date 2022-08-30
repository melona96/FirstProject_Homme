package com.ezen.view.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.biz.cart.CartService;
import com.ezen.biz.dto.MemberVO;
import com.ezen.biz.dto.OrderVO;
import com.ezen.biz.member.MemberService;
import com.ezen.biz.order.OrderService;


@Controller
public class MyPageController {

	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/mypage_form", method=RequestMethod.GET)
	public String MypageView(HttpSession session, OrderVO vo, Model model) {
		
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		if (loginUser == null) {
			
			return "member/login";
		} else {
			
			vo.setId(loginUser.getId());
			
			
			List<Integer> oseqList = orderService.getOrder(vo);
			
			List<OrderVO> orderList = new ArrayList<>();
			for(int oseq : oseqList) {
				OrderVO orderVO = new OrderVO();
				orderVO.setId(loginUser.getId());
				orderVO.setOseq(oseq);
				
				List<OrderVO> orders = orderService.getListOrder(orderVO);
				
				OrderVO summary = new OrderVO();
				summary = orders.get(0);
				if(orders.size() > 1) {
					summary.setPname(orders.get(0).getPname() + " 외" + (orders.size()-1) + "건");
				} else {
					summary.setPname(orders.get(0).getPname());
				}
				
				int amount = 0;
				for (OrderVO order : orders) {
					amount += (order.getQuantity() * order.getprice());
				}
				summary.setprice(amount);
				
				orderList.add(summary);
				
			}
			
			
			
			model.addAttribute("orderList", orderList);
			
			return "mypage/mypage";
		}
	}
	
	@RequestMapping(value="/order_detail", method=RequestMethod.GET)
	public String OrderDetailView(Model model, OrderVO vo, HttpSession session) {
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if(loginUser == null) {
			return "member/login";
		} else {
			vo.setId(loginUser.getId());
			
			List<OrderVO> orderList = orderService.getListOrder(vo);
			
			OrderVO orderDetail = new OrderVO();
			orderDetail.setOseq(orderList.get(0).getOseq());		// 주문번호 저장
			orderDetail.setIndate(orderList.get(0).getIndate());
			
			int totalAmount = 0;
			for (int i=0; i<orderList.size(); i++) {
				totalAmount += (orderList.get(i).getQuantity() * orderList.get(i).getprice());
			}
			
			model.addAttribute("orderDetail", orderDetail);
			model.addAttribute("orderPrice", totalAmount);
			model.addAttribute("orderList", orderList);
			
			return "mypage/orderDetail";
		}	
	}
	
	
}
