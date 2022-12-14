package com.ezen.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.MemberVO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	// 회원 상세정보 조회
	public MemberVO getMember(String id) {
		
		return mybatis.selectOne("memberMapper.getMember", id);
	}
	
	// 회원 리스트 조회
	public List<MemberVO> getMemberList() {
		return mybatis.selectList("memberMapper.getMemberList");
	}
	
	// 회원ID 존재 확인
	public int confirmID(String id) {
		
		String pwd = mybatis.selectOne("memberMapper.confirmID", id);
	
		if (pwd != null) {
			return 1;	// id 존재
		} else {
			return -1;	// id 존재하지 않음
		}
	}
	
	// 로그인
	public int loginID(MemberVO vo) {
		int result = -1;
		String pwd = mybatis.selectOne("memberMapper.confirmID", vo.getId());
		
		// 테이블에서 조회한 pwd와 사용자가 pwd 비교
		if (pwd == null) {
			result = -1;	// ID가 존재하지 않음
		} else if (pwd.equals(vo.getPwd())) { // pwd: DB에서 조회한 암호
			                                  // vo.getPwd() : 화면 pwd
			result = 1;		// 정상적인 사용자
		} else {
			result = 0;		// 비밀번호 불일치
		}
		
		return result;
	}
	
	// 회원가입
	public void insertMember(MemberVO vo) {
		
		mybatis.insert("memberMapper.insertMember", vo);
	}

	public void deleteMember(String id) {
		mybatis.delete("memberMapper.deleteMember", id);
	}
	
	public void changePwd(MemberVO vo) {
		mybatis.update("memberMapper.changePwd", vo);
	}
}

















