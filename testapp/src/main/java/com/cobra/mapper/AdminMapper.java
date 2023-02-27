package com.cobra.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cobra.vo.CategoryVO;
import com.cobra.vo.GoodsVO;
import com.cobra.vo.GoodsViewVO;

public interface AdminMapper {

	// 카테고리
	@Select("select level, cateName, cateCode, cateCodeRef from goods_category"
			+ "    start with cateCodeRef is null connect by prior cateCode = cateCodeRef")
	public List<CategoryVO> category();
	
	@Insert("insert into tbl_goods (gdsNum, gdsName, cateCode, gdsPrice, gdsStock, gdsDes,"
			+ "       gdsImg, gdsThumbImg)"
			+ "     values (tbl_goods_seq.nextval, #{gdsName}, #{cateCode}, #{gdsPrice}, #{gdsStock}, #{gdsDes},"
			+ "        #{gdsImg}, #{gdsThumbImg})")
	public void register(GoodsVO vo);
	
	@Select("select"
			+ "  gdsNum, gdsThumbImg, gdsName, cateCode, gdsPrice, gdsStock, gdsDes, gdsImg, gdsDate"
			+ "    from tbl_goods"
			+ "        order by gdsNum desc")
	public List<GoodsVO> goodslist();

	// 상품 조회 + 카테고리 조인 
	@Select(" select"
			+ "     g.gdsNum, g.gdsName, g.cateCode, c.cateCodeRef, c.cateName, gdsPrice, gdsStock, gdsDes, gdsImg, gdsDate,"
			+ "     g.gdsImg, g.gdsThumbImg"
			+ "         from tbl_goods g"
			+ "             inner join goods_category c"
			+ "                 on g.cateCode = c.cateCode"
			+ "            where g.gdsNum = #{gdsNum}")
	public GoodsViewVO goodsView(int gdsNum);
	
	@Update("update tbl_goods"
			+ "     set"
			+ "         gdsName = #{gdsName},"
			+ "         cateCode = #{cateCode},"
			+ "         gdsPrice = #{gdsPrice},"
			+ "         gdsStock = #{gdsStock},"
			+ "         gdsDes = #{gdsDes}"
			+ "     where gdsNum = #{gdsNum}")
	public void goodsModify(GoodsVO vo);
	
	@Delete("delete"
			+ "     tbl_goods"
			+ " where gdsNum = #{gdsNum}")
	public void goodsDelete(int gdsNum);
}
