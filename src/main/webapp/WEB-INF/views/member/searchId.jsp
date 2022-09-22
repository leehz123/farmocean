<%@page import="com.ezen.farmocean.member.dto.LoginMember"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>아이디찾기</title>
<link rel="stylesheet"
	href="/farmocean/resources/js/member/joinDesign.css">
<%@ include file="/resources/jspf/header.jspf"%>
</head>

<body>
	<%@ include file="/resources/jspf/body_header.jspf"%>


	<section class="section1">
		<h1 style="text-align: center; padding-bottom: 2%;">아이디 찾기</h1>
		<section class="section2">
			<table>
				<tr style="margin-top: 100px;">
					<td class="col1">이름</td>
					<td class="col2"><input placeholder="이름을 입력해주세요." id="member_name" name="member_name"></td>
				</tr>
				<tr>
					<td class="col1">이메일</td>
					<td class="col2"><input placeholder="이메일을 입력해주세요." type="text" id="member_email"
						name="member_email">
						<div id="out"></div></td>
				</tr>
			</table>
		</section>
		<div class="create">
			<button class="but3" id="post_search_btn">조회하기</button>
		</div>


	</section>

	<script src="/farmocean/resources/js/member/idSearch.js"></script>

	<%@ include file="/resources/jspf/body_footer.jspf"%>
</body>


</html>