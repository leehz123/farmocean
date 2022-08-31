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
	<h1>비밀번호 찾기</h1>


	<table border="1" width="400px">
		<tr>
			<td>아이디</td>
			<td><input id="member_id" name="member_id"></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" id="member_email" name="member_email"></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center">
				<div id="out">　</div>
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<button id="post_search_btn">조회하기</button>
			</td>
		</tr>
		
		
	</table>
	
	<button type="button" id="id_search_btn">아이디찾기</button>
	<button type="button" id="home_btn">로그인</button>

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