package com.cobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobra.mapper.AdminMapper;
import com.cobra.vo.CategoryVO;
import com.cobra.vo.GoodsVO;
import com.cobra.vo.GoodsViewVO;

@Service
public class AdminService {

	@Autowired
	public AdminMapper adminmapper;
	
	public List<CategoryVO> category(){
		return adminmapper.category();
	}
	
	public void register(GoodsVO vo) {
		adminmapper.register(vo);
	}
	
	public List<GoodsVO> goodslist(){
		return adminmapper.goodslist();
	}
	
	public GoodsViewVO goodsView(int gdsNum) {
		return adminmapper.goodsView(gdsNum);
	}
	
	public void goodsModify(GoodsVO vo) {
		adminmapper.goodsModify(vo);
	}
	
	public void goodsDelete(int gdsNum) {
		adminmapper.goodsDelete(gdsNum);
	}
}
