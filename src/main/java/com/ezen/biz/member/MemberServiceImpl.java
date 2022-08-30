package com.ezen.biz.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.MemberDAO;
import com.ezen.biz.dto.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO mDao;
	
	@Override
	public MemberVO getMember(String id) {
		
		return mDao.getMember(id);
	}

	@Override
	public int confirmID(String id) {
		
		return mDao.confirmID(id);
	}

	@Override
	public void insertMember(MemberVO vo) {
		
		mDao.insertMember(vo);
	}

	@Override
	public int loginID(MemberVO vo) {
		
		return mDao.loginID(vo);
	}

	@Override
	public List<MemberVO> getMemberList() {
		
		return mDao.getMemberList();
	}

	@Override
	public void deleteMember(String id) {
		mDao.deleteMember(id);
		
	}

	@Override
	public void changePwd(MemberVO vo) {
		mDao.changePwd(vo);
		
	}

}
