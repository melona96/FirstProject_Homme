package com.ezen.biz.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.QnaDAO;
import com.ezen.biz.dto.QnaVO;

import utils.Criteria;

@Service("qnaService")
public class QnaServiceImpl implements QnaService {

	@Autowired
	private QnaDAO qnaDao;
	
	@Override
	public List<QnaVO> getQnaList() {
		return qnaDao.getQnaList();
	}
	
	@Override
	public QnaVO getQna(int qseq) {
		
		return qnaDao.getQna(qseq);
	}
	
	@Override
	public void insertQna(QnaVO vo) {
		
		qnaDao.insertQna(vo);
	}

	@Override
	public void updateQna(QnaVO vo) {
		qnaDao.updateQna(vo);
	}

	@Override
	public void deleteQna(int qseq) {
		qnaDao.deleteQna(qseq);
		
	}

	@Override
	public List<QnaVO> getQnaListWithPaging(Criteria criteria) {
		return qnaDao.getQnaListWithPaging(criteria);
	}

	@Override
	public int getCountQna() {
		return qnaDao.getCountQna();
	}

	
	
	
}
