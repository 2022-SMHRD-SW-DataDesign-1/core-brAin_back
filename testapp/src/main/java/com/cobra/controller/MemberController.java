package com.cobra.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cobra.service.MemberService;
import com.cobra.vo.MemberVO;

@CrossOrigin
@RestController
@RequestMapping("/member/*")
public class MemberController {

	@Autowired
	private MemberService memberservice;

	@Autowired
	BCryptPasswordEncoder passEncoder;

	// 회원 가입 get
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public void getSignup() throws Exception {

	}

	// 회원 가입 post
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String postSignup(MemberVO vo) throws Exception {

		String inputPass = vo.getUserPass();
		String pass = passEncoder.encode(inputPass);
		vo.setUserPass(pass);

		memberservice.signup(vo);

		return "redirect:/";
	}
	
	/*
	// json 파싱 회원 가입 post
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String postSignup(@RequestBody HashMap<String, Object> map) {

		//String inputPass = vo.getUserPass();
		//String pass = passEncoder.encode(inputPass);
		//vo.setUserPass(pass);
		//memberservice.signup(vo);
		
		Gson gson = new Gson();
		
		JsonElement jsonElement = gson.toJsonTree(map);
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		
		// VO에 있는 필드값을 자동 키값으로 잡아주는듯?
		String userId = jsonObject.get("userId").getAsString();
		System.out.println("userId : " + userId);
		String userJson = ""; // 초기화
		
		try {
			MemberVO loginUser = memberservice.signin(userId);
			System.out.println("test loginUser : "+ loginUser);
			
			MemberVO user = new MemberVO(loginUser.getUserId(),
								loginUser.getUserName(),
								loginUser.getUserPhon()
								);
			
			userJson = gson.toJson(user);
			
		} catch (Exception e) {
			System.out.println("user없을때 실행");
			userJson = "{\"userName\":\"NoUser\"}";
		}
				
		return userJson;
	}
	**/
	

	// 로그인 get
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public void getSignin() throws Exception {

	}

	// 로그인 post
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String postSignin(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception {

		System.out.println("들어옴 " + vo.getClass());
		List<MemberVO> login = memberservice.signin(vo);
		
		
		System.out.println("login : " + login);
		HttpSession session = req.getSession();
		// getUserPass는 실전 VO에 맞게 바꿔주야야함!
		boolean passMatch = passEncoder.matches(vo.getUserPass(), login.get(0).getUserPass());

		if (login != null && passMatch) {
			session.setAttribute("membersession", login);
		} else {
			session.setAttribute("membersession", null);
			rttr.addFlashAttribute("msg", false);
			return "";
		}
		return "";
	}

	// 로그아웃
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String signout(HttpSession session) throws Exception {

		memberservice.signout(session);

		return "redirect:/";
	}

	// 회원정보 수정 get
	@RequestMapping(value = "/updateMember", method = RequestMethod.GET)
	public void getModify(HttpSession session, MemberVO vo) throws Exception {

		System.out.println("get 정보수정 들어옴");
	}

	// 회원정보 수정 post
	@RequestMapping(value = "/updateMember", method = RequestMethod.POST)
	public String postModify(HttpSession session, MemberVO vo) throws Exception {

		String inputPass = vo.getUserPass();
		String pass = passEncoder.encode(inputPass);
		vo.setUserPass(pass);

		memberservice.updateMembers(vo);

		session.invalidate();

		return "redirect:/";
	}
	
	// 회원 탈퇴 get
	@RequestMapping(value = "/deleteMember", method = RequestMethod.GET)
	public void getWithdrawal() throws Exception {
	   
	}
	
	// 회원 탈퇴 post
	@RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
	public String postWithdrawal(HttpSession session, MemberVO vo, RedirectAttributes rttr) throws Exception {
		 MemberVO member = (MemberVO)session.getAttribute("membersession");
		 
		 // 여기서 newPass이거를 다시 encrypt해야하나??? 어케 다시 암호화 해야할지 모르겠음,,,
		 // oldPass 와 newPass 비교 어케해야하지?????? 
		 String oldPass = member.getUserPass();
		 String newPass = vo.getUserPass();
		     
		 if(!(oldPass.equals(newPass))) {
		  rttr.addFlashAttribute("msg", false);
		  return "redirect:/member/deleteMember";
		 }
		 
		 memberservice.deleteMembers(vo);
		 
		 session.invalidate();
		  
		 return "redirect:/";
	}
	
	

}
