<%@page import="com.ezen.farmocean.member.dto.LoginMember"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인 성공!!</h1>

	<% 
		LoginMember member = (LoginMember)session.getAttribute("loginId");
	%>
			
	<div>
		'<%= member.getMember_id() %>'
	</div>
	
</body>


</html>