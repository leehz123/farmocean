<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>���� ���� ������</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>

		<h3>���� ���� ������</h3>
		
		<table border='1'>
		
			<tr>
				<th>�޼��� ��ȣ</th>
				<th>�޴� ���</th>
				<th>�޼��� ����</th>
				<th>�޼��� ����</th>
				<th>�޼��� ���� �ð�</th>
				<th>�޼��� Ȯ�� ����</th>
			</tr>
			
			<c:forEach items="${mysendlist }" var="list">
				<tr>
					<td>${list.message_id }</td>
					<td>${list.recipient_id }</td>
					<td>${list.message_title }</td>
					<td>${list.message_contents }</td>
					<td>${list.message_date }</td>
					<td>${list.message_check }</td>
				</tr>
			</c:forEach>
			
		</table>
		
		<script src="/farmocean/resources/js/mypage/mysendlist.js?ver=<%=System.currentTimeMillis() %>"></script>

</body>
</html>