<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ�� ���� ����</title>
</head>
<body>

		<h3>ȸ�� ���� ����</h3>
		
		<table border='1'>
			
			<c:forEach items="${messageList }" var="list">
				<tr>
					<td>${list.message_id }</td>
					<td>${list.sender_id }</td>
					<td>${list.recipient_id }</td>
					<td>${list.message_title }</td>
					<td>${list.message_contents }</td>
					<td>${list.message_date }</td>
					<c:choose ></c:choose>
					<td>${list.message_check }</td>
					
				</tr>
			</c:forEach>
			
		</table>
		
		<a href="<c:url value="/mypage/main" />">main���� ����</a>

</body>
</html>