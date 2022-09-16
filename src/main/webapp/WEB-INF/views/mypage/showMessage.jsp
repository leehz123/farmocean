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
		<a href="<c:url value="/mypage/mylist" />">�ڷΰ���</a>
		   
		   <c:forEach items="${ids }" var="ids">
		   	   
		   		<button 
		   			onclick="window.open('http://localhost:8888/farmocean/mypage/sendMessages?id=${ids }', 
		   			'', 'width=500, height=600 scrollbars=no, resizable=no, toolbars=no, menubar=no');">
		   			�����ϱ�
		   		</button>
		   
		   </c:forEach>
		   
			<c:forEach items="${messageList }" var="list">
			
				<form action="deleteMessage" method="POST">
						<input type="hidden" id="message_id" name="message_id" value="${list.message_id }"/>
						<input type="submit" value="�����ϱ�" />
				</form>
		   
		
		<table border='1' style = "word-break: break-all">
		
			<tr>
				<th>���� ���</th>
				<th>���� �ð�</th>
				<th>�޼��� ����</th>
				<th>�޼��� ����</th>
			</tr>
			
				<tr>
					<td>${list.sender_id }</td>
					<td>${list.message_date }</td>
					<td>${list.message_title }</td>
					<td>${list.message_contents }</td>
				</tr>
			</c:forEach>
			
		</table>

<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>