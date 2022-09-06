<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.ezen.farmocean.member.dto.Member"%>

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

	<button type="button" id="home_btn">로그인창으로</button>
	<br>
	<br>
	<button type="button" id="join_btn_buyer">구매자 회원가입</button>
	<br>
	<br>
	<button type="button" id="join_btn_seller">판매자 회원가입</button>
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

		</table>

		<tr>
			<td><div id="out"></div></td>
			<td><button id="join_btn">회원가입</button></td>
		</tr>

	</div>


	
	<script src="/farmocean/resources/js/member/naverJoin.js"></script>
	<script>
		const homeBtn = document.getElementById('home_btn');
		const buyerJoinBtn = document.getElementById('join_btn_buyer');
		const sellerJoinBtn = document.getElementById('join_btn_seller');
	
		homeBtn.addEventListener('click',(e)=>{
		    window.location.replace('/farmocean/member/login');
		});
		
		buyerJoinBtn.addEventListener('click',(e)=>{
		    window.location.replace('/farmocean/member/join');
		});
	
		sellerJoinBtn.addEventListener('click',(e)=>{
		    window.location.replace('/farmocean/member/sellerjoin');
		});
	</script>



</body>
</html>

