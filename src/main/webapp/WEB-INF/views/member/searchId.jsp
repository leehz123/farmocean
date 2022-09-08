<%@page import="com.ezen.farmocean.member.dto.LoginMember"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<%@ include file="/resources/jspf/header.jspf" %>
</head>
<style>
	*{
		text-align: center;
	}
</style>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>

	<h1>���̵� ã��</h1>


	<table border="1" width="400px">
		<tr>
			<td>�̸�</td>
			<td><input id="member_name" name="member_name"></td>
		</tr>
		<tr>
			<td>�̸���</td>
			<td><input type="text" id="member_email" name="member_email"></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center">
				<div id="out">��</div>
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<button id="post_search_btn">��ȸ�ϱ�</button>
			</td>
		</tr>
		
		
	</table>
	
	<button type="button" id="pw_search_btn">��й�ȣã��</button>
	<button type="button" id="home_btn">�α���</button>

	<script src="/farmocean/resources/js/member/idSearch.js"></script>
	<script>
		const pwSearchBtn = document.getElementById('pw_search_btn');
			
		const homeBtn = document.getElementById('home_btn');

		homeBtn.addEventListener('click',(e)=>{
		    window.location.replace('/farmocean/member/login');
		});
		
		pwSearchBtn.addEventListener('click',(e)=>{
		    window.location.href='/farmocean/member/searchPw';
		});
	</script>
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>


</html>