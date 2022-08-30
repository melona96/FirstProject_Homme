package com.ezen.biz.member;

import java.util.List;

import com.ezen.biz.dto.MemberVO;

public interface MemberService {

	// 회원 상세정보 조회
	MemberVO getMember(String id);

	List<MemberVO> getMemberList();
	
	// 회원ID 존재 확인
	int confirmID(String id);

	// 회원 인증
	int loginID(MemberVO vo);
	
	// 회원 추가
	void insertMember(MemberVO vo);
	
	void deleteMember(String id);
	
	void changePwd(MemberVO vo);
}