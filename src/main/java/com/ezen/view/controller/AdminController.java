package com.ezen.view.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.biz.admin.AdminService;
import com.ezen.biz.dto.AdminVO;
import com.ezen.biz.dto.MemberVO;
import com.ezen.biz.dto.OrderVO;
import com.ezen.biz.dto.ProductVO;
import com.ezen.biz.dto.QnaVO;
import com.ezen.biz.member.MemberService;
import com.ezen.biz.order.OrderService;
import com.ezen.biz.product.ProductService;
import com.ezen.biz.qna.QnaService;

import utils.Criteria;
import utils.PageMaker;

@Controller
@SessionAttributes("admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	QnaService qnaService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value="/admin_login_form", method=RequestMethod.GET)
	public String AdminLoginView() {
		return "admin/adminLogin";
	}
	
	@RequestMapping(value="/admin_login", method=RequestMethod.POST)
	public String AdminLoginAction(AdminVO vo, Model model) {
		AdminVO admin = null;
		
		int result = adminService.adminCheck(vo);
		
		if (result == 1) {
			admin = adminService.getAdmin(vo.getId());
			model.addAttribute("admin", admin);
			
			return "redirect:admin_main";
			//return "redirect:admin_product_list";
		} else {
			return "admin/login_fail";
		}
	}
	
	
	@RequestMapping(value="/admin_main", method=RequestMethod.GET)
	public String AdminMainView(HttpSession session, Model model, AdminVO vo) {
		
		return "admin/adminMain";
	}
	
	
	@RequestMapping(value="/admin_qna_form", method=RequestMethod.GET)
	public String AdminQnaFormView(@RequestParam(value="key", defaultValue="") String name,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum,
			@RequestParam(value="rowsPerPage", defaultValue="10") int rowsPerPage,
			Model model) {

			Criteria criteria = new Criteria();
			criteria.setPageNum(pageNum);
			criteria.setRowsPerPage(rowsPerPage);
			
			List<QnaVO> qnaList = qnaService.getQnaListWithPaging(criteria);
			
		
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCriteria(criteria); // 현재 페이지와 페이지 당 항목수 저장
			pageMaker.setTotalCount(qnaService.getCountQna());  // 전체 항목 수를 조회하여 저장
			
			model.addAttribute("qnaList", qnaList);
			model.addAttribute("pageMaker", pageMaker);		
				
		return "admin/adminQnaList";
	}
	
	@RequestMapping(value="/admin_memberList", method=RequestMethod.GET)
	public String AdminMemberListView(MemberVO vo, Model model) {
		
		List<MemberVO> memberList = memberService.getMemberList();
		
		model.addAttribute("memberList", memberList);
		
		return "admin/adminMemberList";
		
	}
	
	@RequestMapping(value="/admin_sales_form", method=RequestMethod.GET)
	public String adminSalesView(@RequestParam(value="pageNum", defaultValue="1") int pageNum,
								@RequestParam(value="rowsPerPage", defaultValue="10") int rowsPerPage,
								Model model) {
		
		Criteria criteria = new Criteria();
		criteria.setPageNum(pageNum);
		criteria.setRowsPerPage(rowsPerPage);
		
		List<Integer> oseqList = orderService.getSales(criteria);
		
		List<OrderVO> orderList = new ArrayList<>();
		for(int oseq : oseqList) {
			OrderVO orderVO = new OrderVO();
			orderVO.setOseq(oseq);
			
			List<OrderVO> orders = orderService.getListOrderDetail(orderVO);
			
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
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(orderService.getCountOrder());
		
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("orderList", orderList);
		
		
		return "admin/adminSales";
		
	}
	
	@RequestMapping(value="/admin_order_detail", method=RequestMethod.GET)
	public String adminOrderDetail(Model model, OrderVO vo, HttpSession session) {
		
			
			List<OrderVO> orderList = orderService.getListOrderDetail(vo);
			
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
			
			return "admin/adminOrderDetail";
	}	
	
	
	@RequestMapping(value="/admin_productList", method=RequestMethod.GET)
	public String adminProductListPaging(@RequestParam(value="key", defaultValue="") String name,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum,
			@RequestParam(value="rowsPerPage", defaultValue="10") int rowsPerPage,
			Model model) {

			Criteria criteria = new Criteria();
			criteria.setPageNum(pageNum);
			criteria.setRowsPerPage(rowsPerPage);
			
			List<ProductVO> productList = productService.getProductListWithPaging(criteria);
			
			// 화면에 표시할 페이지 버튼의 정보 설정(PageMaker 객체 이용)
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCriteria(criteria); // 현재 페이지와 페이지 당 항목수 저장
			pageMaker.setTotalCount(productService.getCountProduct());  // 전체 항목 수를 조회하여 저장
			
			model.addAttribute("productList", productList);
			model.addAttribute("pageMaker", pageMaker);
			
			return "admin/adminProductList";
		}
	
	
	@RequestMapping(value="/insert_product_form", method=RequestMethod.GET)
	public String adminProductWriteForm() {
		
		return "admin/adminProductWrite";
	}
	
	@RequestMapping(value="/insert_product_action", method=RequestMethod.POST)
	public String adminProductWriteAction(@RequestParam(value="file1") MultipartFile uploadFile1,
											@RequestParam(value="file2") MultipartFile uploadFile2,
											@RequestParam(value="file3") MultipartFile uploadFile3,
											ProductVO vo, HttpSession session) {
		
		if (!uploadFile1.isEmpty()) {
			String fileName1 = uploadFile1.getOriginalFilename();
			String fileName2 = uploadFile2.getOriginalFilename();
			String fileName3 = uploadFile3.getOriginalFilename();
			
			vo.setImage(fileName1);
			vo.setImage2(fileName2);
			vo.setImage3(fileName3);
		
		// 이미지 파일을 이동할 실제경로 구하기
		String image_path = session.getServletContext()
				.getRealPath("WEB-INF/resources/images/");
		try {
			uploadFile1.transferTo(new File(image_path+fileName1));
			uploadFile2.transferTo(new File(image_path+fileName2));
			uploadFile3.transferTo(new File(image_path+fileName3));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			} 
		}
		productService.insertProduct(vo);
		
		return "redirect: admin_productList";
	}
	
	@RequestMapping(value="/product_update_view", method=RequestMethod.GET)
	public String updateProductView(ProductVO vo, Model model) {
		
		ProductVO product = productService.getProduct(vo);
		
		model.addAttribute("productVO", product);
		
		return "admin/adminProductUpdate";
	}
	
	
	@RequestMapping(value="/product_update_action", method=RequestMethod.POST)
	public String updateProductAction(Model model, ProductVO vo, @RequestParam(value="file1") MultipartFile uploadFile1,
										@RequestParam(value="file2") MultipartFile uploadFile2,
										@RequestParam(value="file3") MultipartFile uploadFile3,
										@RequestParam(value="noImg1") String org_image1,
										@RequestParam(value="noImg2") String org_image2,
										@RequestParam(value="noImg3") String org_image3,
										HttpSession session) {
		
		if (!uploadFile1.isEmpty() && uploadFile2.isEmpty() && uploadFile3.isEmpty()) {
			String fileName1 = uploadFile1.getOriginalFilename();
			
			
			vo.setImage(fileName1);
			vo.setImage2(org_image2);
			vo.setImage3(org_image3);
		
		// 이미지 파일을 이동할 실제경로 구하기
			String image_path = session.getServletContext()
				.getRealPath("WEB-INF/resources/images/");
			try {
				uploadFile1.transferTo(new File(image_path+fileName1));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			  } 
		} else if(!uploadFile2.isEmpty() && uploadFile1.isEmpty() && uploadFile3.isEmpty()) {
	
			String fileName2 = uploadFile2.getOriginalFilename();
			
			vo.setImage(org_image1);
			vo.setImage2(fileName2);
			vo.setImage3(org_image3);
			
			String image_path = session.getServletContext()
					.getRealPath("WEB-INF/resources/images/");
			try {
				uploadFile2.transferTo(new File(image_path+fileName2));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		} else if(!uploadFile3.isEmpty() && uploadFile1.isEmpty() && uploadFile2.isEmpty()) {
			
			String fileName3 = uploadFile3.getOriginalFilename();
			
			vo.setImage(org_image1);
			vo.setImage2(org_image2);
			vo.setImage3(fileName3);
			
			String image_path = session.getServletContext()
					.getRealPath("WEB-INF/resources/images/");
			try {
				uploadFile3.transferTo(new File(image_path+fileName3));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			} 	
		} else {
			String fileName1 = uploadFile1.getOriginalFilename();
			String fileName2 = uploadFile2.getOriginalFilename();
			String fileName3 = uploadFile3.getOriginalFilename();
			
			vo.setImage(fileName1);
			vo.setImage2(fileName2);
			vo.setImage3(fileName3);
			
			String image_path = session.getServletContext()
					.getRealPath("WEB-INF/resources/images/");
			try {
				uploadFile1.transferTo(new File(image_path+fileName1));
				uploadFile2.transferTo(new File(image_path+fileName2));
				uploadFile3.transferTo(new File(image_path+fileName3));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		productService.updateProduct(vo);
		
		return "redirect: admin_productList";
	}
	
	@RequestMapping(value="/delete_product", method=RequestMethod.GET) 
	public String deleteProductAction(ProductVO vo) {
		
		int pseq = vo.getPseq();
		
		productService.deleteProduct(pseq);
		
		return "redirect: admin_productList";
	}
	
}
