<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/resources/jspf/header.jspf" %>
<title>회원 정보 수정</title>
<style>
td{
border-top: 100px;
}
</style>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>

		<h3>회원 정보 수정 (판매자)</h3>


	<form method="post">
		<table border="1" width="400px">
			<tr>
				<td>현재 비밀번호</td>
				<td><input id="member_pw" name="member_pw"></td>
				<br>
			</tr>
			<tr>
				<td>새 비밀번호</td>
				<td><input type="password" id="member_new_pw" name="member_pw"></td>
			</tr>
			<tr>
				<td>새 비밀번호 확인</td>
				<td><input type="password" id="member_new_pw_check" name="member_pw"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					
					<button formaction="/farmocean/member/logincheck" type="submit"
						id="submit">로그인</button> <br> <br>
				</td>
			</tr>
		</table>
	</form>
	</table>

	<script src="/farmocean/resources/js/mypage/changeinfo.js?ver=<%=System.currentTimeMillis() %>"></script>
		<script src="/farmocean/resources/js/mypage/changeinfo2.js?ver=<%=System.currentTimeMillis() %>"></script>
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		
<%@ include file="/resources/jspf/body_footer.jspf" %>		
</body>
</html>