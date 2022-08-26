<%@page import="com.ezen.farmocean.member.dto.Member"%>

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
	
		Member member = (Member)session.getAttribute("loginId");
		String memberType = (String)session.getAttribute("memberType");

	%>
			

	
	

	<table border="1">
		<tr><td>로그인 아이디 </td><td id="logined_id">[<%= member.getMember_id() %>]님 환영해요.</td></tr>
		<tr><td>판매지역</td><td id="logined_point">[<%= member.getMember_address()  %>]</td></tr> 
		<tr><td>판매자 이름</td><td id="logined_name">[<%=member.getMember_name() %>]</td></tr> 
		<tr><td>회원 등급</td><td id="logined_class"><%=memberType %></td></tr>
	</table>
	
	<br><br>
	
	<button id="logout_btn">로그아웃 버튼</button>
	
	
	<script>
		const logout = document.getElementById('logout_btn');
		
		logout.addEventListener('click',(e)=>{
			<%
				session.invalidate();
			%>
		window.location.replace('/farmocean/member/login');
		});
	</script>
</body>


</html>