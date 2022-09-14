<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>���� ���� ���� ����</title>
		<style>
			table {
				width: 1280px;
			}
			td {
				padding: 20px;
				border: 1px solid #666666;
			}
			th {
				padding: 20px;
				border: 1px solid #666666;
			}
		</style>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>

		<h3>���� ���� ���� ����</h3> 

		<table border='1' style = "word-break: break-all">
		
			<tr>
				<th>�޴� ���</th>
				<th>���� �ð�</th>
				<th>�޼��� ����</th>
				<th>�޼��� ����</th>
			</tr>
			
			<c:forEach items="${messageList }" var="list">
				<tr>
					<td>${list.recipient_id }</td>
					<td>${list.message_date }</td>
					<td>${list.message_title }</td>
					<td>${list.message_contents }</td>
				</tr>
			</c:forEach>
			
		</table>
		
		<a href="<c:url value="/mypage/mysendlist" />">���� ���� ����</a>

<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>