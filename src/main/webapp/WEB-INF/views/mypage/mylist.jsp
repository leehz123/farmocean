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

		<table id="mymessage" border="1">
		
			<tr>
				<th>�޼��� ��ȣ</th>
				<th>���� ���</th>
				<th>�޼��� ����</th>
				<th>�޼��� ����</th>
				<th>�޼��� ���� �ð�</th>
				<th>�޼��� Ȯ�� ����</th>
			</tr>
			
		</table>
		
			<c:forEach items="${myID }" var="ID">
					<div id="realid" style="display:none;">${ID }</div>
			</c:forEach>	
		
		<script src="/farmocean/resources/js/mypage/mylist.js?ver=<%=System.currentTimeMillis() %>"></script>
</body>
</html>