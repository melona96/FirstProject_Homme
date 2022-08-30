package com.ezen.biz.qna;

import java.util.List;

import com.ezen.biz.dto.QnaVO;

import utils.Criteria;

public interface QnaService {

	List<QnaVO> getQnaList();

	QnaVO getQna(int qseq);
	
	List<QnaVO> getQnaListWithPaging(Criteria criteria);
	
	int getCountQna();

	void insertQna(QnaVO vo);
	
	void updateQna(QnaVO vo);

	void deleteQna(int qseq);
}