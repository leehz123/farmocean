<%@page import="com.ezen.farmocean.member.dto.LoginMember"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>비밀번호 찾기</title>
<link rel="stylesheet"
	href="/farmocean/resources/js/member/joinDesign.css">
<%@ include file="/resources/jspf/header.jspf" %>
</head>

<body>
<%@ include file="/resources/jspf/body_header.jspf" %>

	<section class="section1">
		<h1 style="text-align: center; padding-bottom: 2%;">비밀번호 찾기</h1>
		<section class="section2">
		<table>
			<tr style="margin-top: 30px;">
				<td  class="col1">아이디</td>
				<td class="col2"><input placeholder="아이디를 입력해주세요." id="member_id" name="member_id"></td>
			</tr>
			<tr>
				<td class="col1">이메일</td>
				<td class="col2"><input placeholder="이메일을 입력해주세요." type="text" id="member_email" name="member_email">
				<div id="out"></div></td>
			</tr>
		</table>


		</section>
			<div class="create">
				<button class="but3" id="post_search_btn">조회하기</button>
			</div>
			</section>


	<script src="/farmocean/resources/js/member/pwSearch.js"></script>

<%@ include file="/resources/jspf/body_footer.jspf" %>	
</body>


</html>