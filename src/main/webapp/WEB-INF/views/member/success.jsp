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
		String member_type = (String)(member.getMember_type()) == "B" ? "구매자": "판매자";
	%>
			

	<table border="1">
		<tr><td>로그인 아이디 </td><td id="logined_id">[<%= member.getMember_id() %>]님 환영해요.</td></tr>
		<tr><td>잔여 포인트</td><td id="logined_point">[<%= member.getMember_point()  %>]원</td></tr> 
		<tr><td>로그인 이름</td><td id="logined_name">[<%= member.getMember_name() %>]</td></tr> 
		<tr><td>회원 등급</td><td id="logined_class">[<%=member_type %>]</td></tr>
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