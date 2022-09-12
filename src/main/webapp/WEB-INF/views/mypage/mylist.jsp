<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head> 
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>���� ���� ������</title>
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
					
		<h3>���� ���� ������</h3> 

		<table id="mymessage" border="1" style = "word-break: break-all">
		
			<tr>
				<th>��ȣ</th>
				<th>���� ���</th>
				<th>�޼��� ����</th>
				<th>���� �ð�</th>
				<th>���� �ð�</th>
				<th>Ȯ�� ����</th>
			</tr>
			
		</table>
		
			<c:forEach items="${myID }" var="ID">
					<div id="realid" style="display:none;">${ID }</div>
			</c:forEach>	
		
		<script src="/farmocean/resources/js/mypage/mylist.js?ver=<%=System.currentTimeMillis() %>"></script>
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>