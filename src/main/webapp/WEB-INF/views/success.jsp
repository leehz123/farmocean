<%@page import="com.ezen.farmocean.member.dto.SellMember"%>
<%@page import="com.ezen.farmocean.member.dto.BuyMember"%>
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
		BuyMember buyer = (BuyMember)session.getAttribute("loginId");
		SellMember seller = (SellMember)session.getAttribute("loginId");
		
	%>

	<table border="1">
		<tr><td>�α��� ���̵� </td><td id="logined_id">[<%= buyer.getBuy_id() %>]�� ȯ���ؿ�.</td></tr>
		<tr><td>�ܿ� ����Ʈ</td><td id="logined_point">[<%=buyer.getBuy_point()  %>]��</td></tr> 
		<tr><td>�α��� �̸�</td><td id="logined_name">[<%=buyer.getBuy_name() %>]</td></tr> 
		<tr><td>ȸ�� ���</td><td id="logined_class">�Ϲ�ȸ��</td></tr>
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