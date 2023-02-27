package com.cobra.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cobra.vo.MemberVO;

// url에 admin/index 쳐도 관리자 아니면 못들어가게하는 기능
public class AdminInterceptor extends HandlerInterceptorAdapter {

 @Override
 public boolean preHandle(HttpServletRequest req,
    HttpServletResponse res, Object obj) throws Exception {
  
  HttpSession session = req.getSession();
  MemberVO member = (MemberVO)session.getAttribute("membersession");
  
  // 비로그인시 접속 못하게
  if(member == null) {
	   res.sendRedirect("/member/signin");
	   return false;
  }
  // 관리자 9번이 아닐시 접속 못하게
  if(member.getVerify() != 9) {
	  res.sendRedirect("/");
	  return false;
  }
  
  return true;
 }
}
