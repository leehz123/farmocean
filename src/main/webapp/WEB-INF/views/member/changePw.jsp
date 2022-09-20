<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.ezen.farmocean.member.dto.LoginMember"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/resources/jspf/header.jspf" %>
<title>회원 정보 수정</title>
<link rel="stylesheet"
	href="/farmocean/resources/js/member/joinDesign.css">


</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>

		<h3>비밀번호 변경</h3>


	<form method="post" id="form">
				<p>
					※비밀번호는 <span class="num">문자, 숫자, 특수문자(~!@#$%^&*)의 조합 8 ~
						15자리</span>로 입력이 가능합니다.
						
				</p>
				<p>			
					※안전한 비밀번호로 내정보를 보호하세요
				</p>
				<p>
					※다른 아이디/사이트에서 사용한 적 없는 비밀번호
				</p>
				<p>				
					※이전에 사용한 적 없는 비밀번호가 안전합니다.
				</p>
		<table>
			<tr>
				<td class="co1"><span class="starEm">*</span>현재 비밀번호</td>
				<td class="co2"><input id="pw_field" placeholder="현재 비밀번호를 입력해주세요" maxlength="15">
				<div id="pw_out"></div></td>
			</tr>
			<tr>
			<td class="col1"><span class="starEm">*</span>새 비밀번호</td>
			<td class="col2"><input type="password" id="member_pw"
				placeholder="새 비밀번호를 입력해주세요" maxlength="15" name="member_pw">
				<div id="pw_out3"></div></td>
		</tr>
			<tr>
				<td class="co1"><span class="starEm">*</span>새 비밀번호 확인</td>
				<td class="co2"><input type="password" id="member_pw_check" 
				placeholder="새 비밀번호를 입력해주세요" maxlength="15" name="member_pw_check">
				<div id="pw_out2"></div></td>
			</tr>
		</table>
		<div class="create">
		<button class="but3" id="submit" >비밀번호 변경</button>
		
	</div>
	</form>
	</table>
	<script>
	</script>

		<script src="/farmocean/resources/js/member/changePw.js"></script>
<%@ include file="/resources/jspf/body_footer.jspf" %>		
</body>
</html>