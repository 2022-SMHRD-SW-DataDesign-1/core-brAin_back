<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>회원 탈퇴</title>
</head>
<body>
	<div id="root">
		<header id="header">
			<div id="header_box">
				<%@ include file="/WEB-INF/views/include/header.jsp"%>
			</div>
		</header>

		<nav id="nav">
			<div id="nav_box">
				<%@ include file="/WEB-INF/views/include/nav.jsp"%>
			</div>
		</nav>
		
	<c:if test="${membersession != null }">		
		<section id="container">
			<div id="container_box">
				<section id="content">
					<form role="form" method="post" autocomplete="off">
						<div class="input_area">
							<label for="userId">아이디</label> <input type="email" id="userId"
								name="userId" value="${membersession.userId }" placeholder="현재 비밀번호 입력"
								required="required" />
						</div>

						<div class="input_area">
							<label for="userPass">패스워드</label> <input type="password"
								id="userPass" name="userPass" required="required" />
						</div>
						
						<button type="submit" id="signup_btn" name="signup_btn">회원 탈퇴</button>

					</form>
				</section>
				<p><a href="/">처음으로</a></p>
			</div>
		</section>
	</c:if>

<c:if test="${msg == false }">
 <p>
 입력한 비밀번호가 잘 못 되었습니다.
 </p>
</c:if>
		
		<footer id="footer">
			<div id="footer_box">
				<%@ include file="/WEB-INF/views/include/footer.jsp"%>
			</div>
		</footer>
	</div>
</body>
</html>