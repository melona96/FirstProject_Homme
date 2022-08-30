package com.ezen.view.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.biz.dto.MemberVO;
import com.ezen.biz.dto.QnaVO;
import com.ezen.biz.qna.QnaService;

import utils.Criteria;
import utils.PageMaker;



@Controller
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	
	// 게시글 상세보기
	@RequestMapping(value="/qna_view", method=RequestMethod.GET)
	public String getQna(QnaVO vo, Model model) {
		
		QnaVO qna = qnaService.getQna(vo.getQseq());
		model.addAttribute("qnaVO", qna);
		
		return "qna/qnaView";
	}
	
	@RequestMapping(value="/qna_write_form", method=RequestMethod.GET)
	public String QnaWriteView(HttpSession session) {
	
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		if (loginUser == null) {
			
			return "member/login";
		} else {
			return "qna/qnaWrite";
		}
	}
	
	@RequestMapping(value="/qna_write", method=RequestMethod.POST)
	public String QnaWriteAction(HttpSession session, QnaVO vo) {
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		vo.setId(loginUser.getId());
		qnaService.insertQna(vo);
		
		return "redirect:qna_form";
	}
	
	
	@RequestMapping(value="/qna_answer_form", method=RequestMethod.GET)
	public String QnaAnswerView(HttpSession session, Model model, QnaVO vo) {
		
		QnaVO qna = qnaService.getQna(vo.getQseq());
		model.addAttribute("qnaVO", qna);
		
		return "qna/qnaAnswer";
	
	}
	
	@RequestMapping(value="/qna_answer", method=RequestMethod.POST)
	public String QnaAnswerAction(QnaVO vo, HttpSession session) {
		
		qnaService.updateQna(vo);
		
		return "redirect:admin_qna_form";
	}
	
	@RequestMapping(value="/qna_delete", method=RequestMethod.GET)
	public String QnaDeleteAction(int qseq, HttpServletRequest request) {
		
		String Referer = request.getHeader("Referer");
		
		qnaService.deleteQna(qseq);
		
		return "redirect: " + Referer;
	}
	
	
	// qna 페이징처리 조회
	@RequestMapping(value="/qna_form", method=RequestMethod.GET)
	public String qnaViewWithPaging(@RequestParam(value="key", defaultValue="") String name,
													@RequestParam(value="pageNum", defaultValue="1") int pageNum,
													@RequestParam(value="rowsPerPage", defaultValue="10") int rowsPerPage,
													Model model) {
		
		Criteria criteria = new Criteria();
		criteria.setPageNum(pageNum);
		criteria.setRowsPerPage(rowsPerPage);
		
		List<QnaVO> qnaList = qnaService.getQnaListWithPaging(criteria);
		
		// 화면에 표시할 페이지 버튼의 정보 설정(PageMaker 객체 이용)
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria); // 현재 페이지와 페이지 당 항목수 저장
		pageMaker.setTotalCount(qnaService.getCountQna());  // 전체 항목 수를 조회하여 저장
		
		model.addAttribute("qnaList", qnaList);
		model.addAttribute("pageMaker", pageMaker);
		
		return "qna/qna";
	}
	
}
