package com.cobra.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cobra.vo.MemberVO;

public interface MemberMapper {

	// 회원가입
	@Insert("insert into tbl_member(userId, userPass, userName, userPhon)"
			+ "	    values(#{userId}, #{userPass}, #{userName}, #{userPhon})")
	public void signup(MemberVO vo);

	// 로그인
	@Select("select userId, userName, userPass, userPhon, userAddr1, userAddr2, userAddr3, regiDate, verify"
			+ "	   from tbl_member" + "	   where userId = #{userId}")
	public List<MemberVO> signin(MemberVO vo);

	// 회원 정보 수정
	@Update("update tbl_member" + "     set" + "         userPass = #{userPass}" + "     where userId = #{userId}")
	public void updateMembers(MemberVO vo);

	// 회원 탈퇴
	@Delete("delete from tbl_member"
			+ "     where userId = #{userId}"
			+ "         and userPass = #{userPass}")
	public void deleteMembers(MemberVO vo);

}
