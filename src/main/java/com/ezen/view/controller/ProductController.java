package com.ezen.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.biz.dto.ProductVO;
import com.ezen.biz.dto.ReviewVO;
import com.ezen.biz.product.ProductService;
import com.ezen.biz.review.ReviewService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ReviewService reviewService;
	
	@RequestMapping(value="/product_detail", method=RequestMethod.GET)
	public String productDetailAction(ProductVO vo, Model model) {
		
		// 상품 상세조회 서비스 호출
		ProductVO product = productService.getProduct(vo);
		
		model.addAttribute("productVO", product);
		
		List<ReviewVO> reviewList = reviewService.getReviewList(vo.getPseq());
		
		model.addAttribute("reviewList", reviewList);
		
		int reviewCnt = reviewService.countReview(vo.getPseq());
		
		model.addAttribute("reviewCnt", reviewCnt);
		
		return "product/productDetail";
		
	}
	
	@RequestMapping(value="/productList", method=RequestMethod.GET)
	public String productListView(ProductVO vo, Model model) {
		
		List<ProductVO> ProductList = productService.getProductList();
		
		model.addAttribute("ProductList", ProductList);
		
		return "product/productList";
	}
	
	@RequestMapping(value="/search_product", method=RequestMethod.GET)
	public String SearchProductView(ProductVO vo, Model model, @RequestParam(value="search") String search) {
		
		List<ProductVO> ProductList = productService.searchProduct(search);
		
		model.addAttribute("ProductList", ProductList);
		
		return "product/productList";
	}
}







