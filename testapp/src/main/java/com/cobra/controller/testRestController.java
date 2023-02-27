package com.cobra.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cobra.service.AdminService;
import com.cobra.service.MemberService;
import com.cobra.vo.GoodsVO;
import com.cobra.vo.MemberVO;

@CrossOrigin
@RestController
public class testRestController {

	/*
	 * @Autowired public AdminService adminservice;
	 * 
	 * @Autowired public MemberService memberservice;
	 * 
	 * //@PostMapping("/board/list") public MemberVO testReact(MemberVO vo) {
	 * System.out.println("들어옴!!!" + vo); List<GoodsVO> list =
	 * adminservice.goodslist(); System.out.println("userId.getUserId() : " +
	 * vo.getUserId()); MemberVO membervo = memberservice.signin(vo);
	 * 
	 * 
	 * // 이제? 이 list를 json형식으로 바꿔서 리액트에 뿌려줘야할듯? System.out.println("vo : " + vo);
	 * System.out.println("membervo : " + membervo); return membervo; }
	 */

}
