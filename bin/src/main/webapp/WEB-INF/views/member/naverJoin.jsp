<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.ezen.farmocean.member.dto.Member"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ������</title>
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

	<button type="button" id="home_btn">�α���â����</button>
	<br>
	<br>
	<button type="button" id="join_btn_buyer">������ ȸ������</button>
	<br>
	<br>
	<button type="button" id="join_btn_seller">�Ǹ��� ȸ������</button>
	<div class="border">
		<h1>���̹� ȸ������</h1>
		<table border="1">
			<tr>
				<td>���̵�</td>
				<td><input type="text" id="post_naver_id"
					value="<%=member.getMember_id()%>" readonly="readonly">
			<tr>
				<td>�̸�</td>
				<td><input type="text" id="post_naver_name"
					value="<%=member.getMember_name()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>�г���</td>
				<td><input type="text" id="post_naver_nickName"
					value="<%=member.getMember_nickName()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>�̸���</td>
				<td><input type="text" id="post_naver_email"
					value="<%=member.getMember_id()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>��ȭ��ȣ</td>
				<td><input style="width: 29%; text-align: center" type="text"
					id="post_naver_phoneNum1" placeholder="��ȭ��ȣ�� �Է����ּ���."> - <input
					style="width: 29%; text-align: center" type="text"
					id="post_naver_phoneNum2" placeholder="��ȭ��ȣ�� �Է����ּ���."> - <input
					style="width: 29%; text-align: center" type="text"
					id="post_naver_phoneNum3" placeholder="��ȭ��ȣ�� �Է����ּ���."></td>
			</tr>

		</table>

		<tr>
			<td><div id="out"></div></td>
			<td><button id="join_btn">ȸ������</button></td>
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

