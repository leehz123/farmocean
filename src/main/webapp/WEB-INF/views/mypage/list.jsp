<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>����Ʈ</title>
</head>
<body>

		<h3>�޼��� ����Ʈ!</h3> 
		
		<table border='1'>
		
			<tr>
				<th>�޼��� ��ȣ</th>
				<th>�߽���</th>
				<th>������</th>
				<th>�޼��� ����</th>
				<th>�޼��� ����</th>
				<th>�޼��� ���� �ð�</th>
				<th>�޼��� Ȯ�� ����</th>
			</tr>
			
			<c:forEach items="${messageList }" var="list">
				<tr>
					<td>${list.message_id }</td>
					<td>${list.sender_id }</td>
					<td>${list.recipient_id }</td>
					<td>${list.message_title }</td>
					<td>${list.message_contents }</td>
					<td>${list.message_date }</td>
					<td>${list.message_check }</td>
				</tr>
			</c:forEach>
			
		</table>
		
		<a href="<c:url value="/mypage/mylist?id=ȫ�浿" />">ȫ�浿 ���� Ȯ��</a>
</body>
</html>