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
			

	<table border="1">
		<tr><td>로그인 아이디 </td><td id="logined_id">[<%= member.getMember_id() %>]님 환영해요.</td></tr>
		<tr><td>로그인 이름</td><td id="logined_name">[<%= member.getMember_name() %>]</td></tr> 
		<tr><td>닉네임</td><td id="logined_nickName">[<%=member.getMember_nickName() %>]</td></tr>
		<tr><td>회원 등급</td><td id="logined_class">[<%=member.getMember_type() %>]회원</td></tr>
	</table>
	
	<br><br>
	
	<button id="logout_btn">로그아웃 버튼</button>
	<button id="chat_btn">채팅 버튼</button>
	<button id="test_btn">test 버튼</button>
	
	
	<script>
		const logout = document.getElementById('logout_btn');
		const chat = document.getElementById('chat_btn');
		const test = document.getElementById('test_btn');
		
		logout.addEventListener('click',(e)=>{
			
		window.location.replace('/farmocean/member/logout');
		});
		
		chat.addEventListener('click',(e)=>{
	
		window.location.href='/farmocean/echo/chat';
		});
	</script>
</body>


</html>