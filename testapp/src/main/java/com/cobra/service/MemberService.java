package com.cobra.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobra.mapper.MemberMapper;
import com.cobra.vo.MemberVO;
@Service
public class MemberService {
	
	@Autowired
	private MemberMapper membermapper;
	
	// 회원가입
	public void signup(MemberVO vo) {
		membermapper.signup(vo);
	}
	
	// 로그인
	public List<MemberVO> signin(MemberVO vo) {
		return membermapper.signin(vo);
	}
	
	// 로그아웃 걍 세션만 꺼버리기
	public void signout(HttpSession session) {
		session.invalidate();
	}
	
	// 회원 정보 수정
	public void updateMembers(MemberVO vo) {
		membermapper.updateMembers(vo);
	}
	
	// 회원 탈퇴
	public void deleteMembers(MemberVO vo) {
		membermapper.deleteMembers(vo);
	}

}
