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
	<h1>�α��� ����!!</h1>

	<% 
		LoginMember member = (LoginMember)session.getAttribute("loginId");
	%>
			

	<table border="1">
		<tr><td>�α��� ���̵� </td><td id="logined_id">[<%= member.getMember_id() %>]�� ȯ���ؿ�.</td></tr>
		<tr><td>�α��� �̸�</td><td id="logined_name">[<%= member.getMember_name() %>]</td></tr> 
		<tr><td>�г���</td><td id="logined_nickName">[<%=member.getMember_nickName() %>]</td></tr>
		<tr><td>ȸ�� ���</td><td id="logined_class">[<%=member.getMember_type() %>]ȸ��</td></tr>
	</table>
	
	<br><br>
	
	<button id="logout_btn">�α׾ƿ� ��ư</button>
	<button id="chat_btn">ä�� ��ư</button>
	<button id="test_btn">test ��ư</button>
	
	
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