<%@page import="com.ezen.farmocean.member.dto.LoginMember"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���̵�ã��</title>
<link rel="stylesheet"
	href="/farmocean/resources/js/member/joinDesign.css">
<%@ include file="/resources/jspf/header.jspf"%>
</head>

<body>
	<%@ include file="/resources/jspf/body_header.jspf"%>


	<section class="section1">
		<h1 style="text-align: center; padding-bottom: 2%;">���̵� ã��</h1>
		<section class="section2">
			<table>
				<tr style="margin-top: 100px;">
					<td class="col1">�̸�</td>
					<td class="col2"><input placeholder="�̸��� �Է����ּ���." id="member_name" name="member_name"></td>
				</tr>
				<tr>
					<td class="col1">�̸���</td>
					<td class="col2"><input placeholder="�̸����� �Է����ּ���." type="text" id="member_email"
						name="member_email">
						<div id="out"></div></td>
				</tr>
			</table>
		</section>
		<div class="create">
			<button class="but3" id="post_search_btn">��ȸ�ϱ�</button>
		</div>


	</section>

	<script src="/farmocean/resources/js/member/idSearch.js"></script>

	<%@ include file="/resources/jspf/body_footer.jspf"%>
</body>


</html>