package com.cobra.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cobra.service.AdminService;
import com.cobra.utils.UploadFileUtils;
import com.cobra.vo.CategoryVO;
import com.cobra.vo.GoodsVO;
import com.cobra.vo.GoodsViewVO;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

	@Inject
	public AdminService adminservice;

	@Resource(name="uploadPath")
	private String uploadPath;
	
	// admin 계정 페이지
	@GetMapping("/index")
	public void getAdminIndex() {
		System.out.println("come in getAdminIndex");

	}

	// get 상품 등록
	@RequestMapping(value = "/goods/register", method = RequestMethod.GET)
	public void getGoodsRegister(Model model) throws Exception {

		List<CategoryVO> category = null;
		category = adminservice.category();
		model.addAttribute("category", JSONArray.fromObject(category));
	}

	// post 상품 등록
	@RequestMapping(value = "/goods/register", method = RequestMethod.POST)
	public String postGoodsRegister(GoodsVO vo, MultipartFile file) throws Exception {
		
		String imgUploadPath = uploadPath + File.separator + "imgUpload";
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
		String fileName = null;

		if(file != null) {
		   fileName =  UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);   
		} else {
		   fileName = uploadPath + File.separator + "images" + File.separator + "none.png";
		}

		vo.setGdsImg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
		vo.setGdsThumbImg(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
		
		adminservice.register(vo);
		
		return "redirect:/admin/index";
	}

	// 상품 목록
	@RequestMapping(value = "/goods/list", method = RequestMethod.GET)
	public void getGoodsList(Model model) throws Exception {

		List<GoodsVO> list = adminservice.goodslist();
		model.addAttribute("list", list);
	}

	// 상품 상세 조회
	@RequestMapping(value = "/goods/view", method = RequestMethod.GET)
	public void getGoodsView(@RequestParam("n") int gdsNum, Model model) throws Exception {

		GoodsViewVO goods = adminservice.goodsView(gdsNum);

		model.addAttribute("goods", goods);
	}

	// 상품 수정 get
	@RequestMapping(value = "/goods/modify", method = RequestMethod.GET)
	public void getGoodsModify(@RequestParam("n") int gdsNum, Model model) throws Exception {
		
		GoodsViewVO goods = adminservice.goodsView(gdsNum);
		model.addAttribute("goods", goods);
	}

	// 상품 수정 post
	@RequestMapping(value = "/goods/modify", method = RequestMethod.POST)
	public String postGoodsModify(GoodsVO vo) throws Exception {

		adminservice.goodsModify(vo);

		return "redirect:/admin/index";
	}

	// 상품 삭제
	@RequestMapping(value = "/goods/delete", method = RequestMethod.POST)
	public String postGoodsDelete(@RequestParam("n") int gdsNum) throws Exception {

		adminservice.goodsDelete(gdsNum);

		return "redirect:/admin/index";
	}

}
