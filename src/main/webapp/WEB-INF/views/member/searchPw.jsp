<%@page import="com.ezen.farmocean.member.dto.LoginMember"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<style>
	*{
		text-align: center;
	}
</style>
<body>
	<h1>��й�ȣ ã��</h1>


	<table border="1" width="400px">
		<tr>
			<td>���̵�</td>
			<td><input id="member_id" name="member_id"></td>
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
	
	<button type="button" id="id_search_btn">���̵�ã��</button>
	<button type="button" id="home_btn">�α���</button>

	<script src="/farmocean/resources/js/member/pwSearch.js"></script>
	<script>
		const idSearchBtn = document.getElementById('id_search_btn');
			
		const homeBtn = document.getElementById('home_btn');

		homeBtn.addEventListener('click',(e)=>{
		    window.location.replace('/farmocean/member/login');
		});
		
		idSearchBtn.addEventListener('click',(e)=>{
		    window.location.href='/farmocean/member/searchId';
		});
	</script>
</body>


</html>