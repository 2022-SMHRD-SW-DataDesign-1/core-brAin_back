<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul>
<c:if test="${membersession == null}">
   <li>
    <a href="/member/signin">로그인</a>
   </li>
   <li>
    <a href="/member/signup">회원가입</a>
   </li>
</c:if>

<c:if test="${membersession != null}">
   <c:if test="${membersession.verify == 9}">
   <li>
   <a href="/admin/index">관리자 화면</a>   
	</li>   
	</c:if>
	
	<li>
    ${membersession.userName}님 환영합니다.
   </li>
   <li>
    <a href="/member/updateMember">회원 정보 수정</a>
   </li>
   <li>
    <a href="/member/signout">로그아웃</a>
   </li>
   <li>
    <a href="/member/deleteMember">회원 탈퇴</a>
   </li>
</c:if>
</ul>