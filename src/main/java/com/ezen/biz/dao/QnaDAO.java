package com.ezen.biz.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.QnaVO;

import utils.Criteria;

@Repository
public class QnaDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<QnaVO> getQnaList() {
		return mybatis.selectList("qnaMapper.getQnaList");
	}
	
	public QnaVO getQna(int qseq) {
		return mybatis.selectOne("qnaMapper.getQna", qseq);
	}
	
	public void insertQna(QnaVO vo) {
		mybatis.insert("qnaMapper.insertQna", vo);
	}
	
	public void updateQna(QnaVO vo) {
		mybatis.update("qnaMapper.updateQna", vo);
	}

	public void deleteQna(int qseq) {
		mybatis.delete("qnaMapper.deleteQna", qseq);
		
	}

	public List<QnaVO> getQnaListWithPaging(Criteria criteria) {
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("criteria", criteria);
		
		return mybatis.selectList("qnaMapper.getQnaListWithPaging", map);
	}
	
	public int getCountQna() {
		return mybatis.selectOne("qnaMapper.getCountQna");
	}
	
}
