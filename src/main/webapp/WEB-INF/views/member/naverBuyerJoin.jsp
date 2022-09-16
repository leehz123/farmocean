<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.ezen.farmocean.member.dto.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원가입</title>
<style>
input {
	width: 60%;
}

table {
	text-align: center;
}
</style>
<%@ include file="/resources/jspf/header.jspf"%>
</head>

<body>
	<%@ include file="/resources/jspf/body_header.jspf"%>

	<%
	Member member = (Member) session.getAttribute("naverId");
	%>

	<div class="border">
		<h1>네이버 회원가입</h1>
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" id="post_naver_id"
					value="<%=member.getMember_id()%>" readonly="readonly">
			<tr>
				<td>이름</td>
				<td><input type="text" id="post_naver_name"
					value="<%=member.getMember_name()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td><input type="text" id="post_naver_nickName"
					value="<%=member.getMember_nickName()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" id="post_naver_email"
					value="<%=member.getMember_id()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input style="width: 29%; text-align: center" type="text"
					id="post_naver_phoneNum1" placeholder="전화번호를 입력해주세요."> - <input
					style="width: 29%; text-align: center" type="text"
					id="post_naver_phoneNum2" placeholder="전화번호를 입력해주세요."> - <input
					style="width: 29%; text-align: center" type="text"
					id="post_naver_phoneNum3" placeholder="전화번호를 입력해주세요."></td>
			</tr>
			<tr>
				<td>우편번호</td>
				<td><input type="text" id="sample6_postcode" placeholder="우편번호"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button"
					onclick="sample6_execDaumPostcode()" value="우편번호 찾기"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" id="sample6_address" placeholder="주소"></td>
			</tr>
			<tr>
				<td>참고주소</td>
				<td><input type="text" id="sample6_extraAddress"
					placeholder="참고항목"></td>
			</tr>
			<tr>
				<td>추가주소</td>
				<td><input type="text" id="sample6_detailAddress"
					placeholder="추가주소"></td>
			</tr>

		</table>

		<tr>
			<td><div id="out"></div></td>
			<td><button id="join_btn">회원가입</button></td>
		</tr>

	</div>


	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="/farmocean/resources/js/member/naverJoin.js"></script>




</body>
</html>

