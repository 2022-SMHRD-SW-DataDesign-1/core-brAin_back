<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Core brain</title>
</head>
<body>
<div id="root">
	<header id="header">
		<div id="header_box">
			<%@ include file="/WEB-INF/views/include/header.jsp" %>
		</div>
	</header>

	<nav id="nav">
		<div id="nav_box">
			<%@ include file="/WEB-INF/views/include/nav.jsp" %>
		</div>
	</nav>
	
	<section id="container">
		<div id="container_box">
			본문 영역
		</div>
	</section>
	
	<footer id="footer">
		<div id="footer_box">
			<%@ include file="/WEB-INF/views/include/footer.jsp" %>
		</div>
	</footer>
</div>
</body>
</html>