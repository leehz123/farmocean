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
	<h1>아이디 찾기</h1>


	<table border="1" width="400px">
		<tr>
			<td>이름</td>
			<td><input id="member_name" name="member_name"></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" id="member_email" name="member_email"></td>
		</tr>
		<tr>
			<td colspan="2">
				<button id="post_search_btn">로그인</button>
			</td>
		</tr>
	</table>

	<script src="/farmocean/resources/js/member/idSearch.js"></script>

</body>


</html>