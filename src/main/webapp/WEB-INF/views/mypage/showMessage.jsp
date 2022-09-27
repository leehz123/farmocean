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
				width: 100%;
			}
			th {
				max-width: 200px;
			}
			td {
				max-width: 200px;
			}
			#content {
				max-width: 200px;
				overflow: hidden;
				text-overflow:ellipsis;
			}
			
		</style>
</head>
<body>


		<h3>���� ���� ���� ����</h3> 
		<a href="<c:url value="/mypage/mylist" />">�ڷΰ���</a>
		   

			<c:forEach items="${messageList }" var="list">
			
		   
		
		<table class="table table-hover" border='1' style = "word-break: break-all">
		
			<tr>
				<th>���� ���</th>
				<th>���� �ð�</th>
				<th>�޼��� ����</th>
				<th>�޼��� ����</th>
				<th>�޼��� ����</th>
				<th>�޼��� ����</th>
			</tr>
			
				<tr>
					<td>${list.sender_id }</td>
					<td>${list.message_date }</td>
					<td>${list.message_title }</td>
					<td id="content">${list.message_contents }</td>
					<td>
					<c:forEach items="${ids }" var="ids">
		   			<button type="button" class="btn btn-outline-primary" onClick="fnWinOpen(500, 600, '<c:url value="/mypage/sendMessages?id=${ids }" />'); return false;">
		   			�����ϱ�
		   			</button>
		   			
		   			
		   
		   </c:forEach>
					</td>
					<td>
					<form action="deleteMessage" method="POST">
						<input type="hidden" id="message_id" name="message_id" value="${list.message_id }"/>
						<input type="submit" value="�����ϱ�" class="btn btn-outline-danger" />
					</form>
					</td>
				</tr>
			</c:forEach>
			
		</table>
	

</body>
</html>