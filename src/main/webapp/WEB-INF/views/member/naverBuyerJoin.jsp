<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.ezen.farmocean.member.dto.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<tr>
				<td>�����ȣ</td>
				<td><input type="text" id="sample6_postcode" placeholder="�����ȣ"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button"
					onclick="sample6_execDaumPostcode()" value="�����ȣ ã��"></td>
			</tr>
			<tr>
				<td>�ּ�</td>
				<td><input type="text" id="sample6_address" placeholder="�ּ�"></td>
			</tr>
			<tr>
				<td>�����ּ�</td>
				<td><input type="text" id="sample6_extraAddress"
					placeholder="�����׸�"></td>
			</tr>
			<tr>
				<td>�߰��ּ�</td>
				<td><input type="text" id="sample6_detailAddress"
					placeholder="�߰��ּ�"></td>
			</tr>

		</table>

		<tr>
			<td><div id="out"></div></td>
			<td><button id="join_btn">ȸ������</button></td>
		</tr>

	</div>


	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="/farmocean/resources/js/member/naverJoin.js"></script>




</body>
</html>

