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
			<td colspan="2">
				<button id="post_search_btn">�α���</button>
			</td>
		</tr>
	</table>

	<script src="/farmocean/resources/js/member/idSearch.js"></script>

</body>


</html>