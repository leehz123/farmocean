<%@page import="com.ezen.farmocean.member.dto.SellMember"%>

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
	
		SellMember seller = (SellMember)session.getAttribute("loginId");
		String memberType = (String)session.getAttribute("memberType");

	%>
			

	
	

	<table border="1">
		<tr><td>�α��� ���̵� </td><td id="logined_id">[<%= seller.getSell_id() %>]�� ȯ���ؿ�.</td></tr>
		<tr><td>�Ǹ�����</td><td id="logined_point">[<%= seller.getSell_address()  %>]</td></tr> 
		<tr><td>�Ǹ��� �̸�</td><td id="logined_name">[<%=seller.getSell_name() %>]</td></tr> 
		<tr><td>ȸ�� ���</td><td id="logined_class"><%=memberType %></td></tr>
	</table>
	
	<br><br>
	
	<button id="logout_btn">�α׾ƿ� ��ư</button>
	
	
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