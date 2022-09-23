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
				width: 100%;
			}
			th {
				max-width: 200px;
			}
			td {
				max-width: 200px;
			}
			
		</style>
</head>
<body> 

		<h3>���� ���� ������</h3>

	<ul class="nav nav-tabs">
		<li class="nav-item"><a class="nav-link active"
			aria-current="page" href="<c:url value="/mypage/mylist" />">���� ���� ������</a></li>
		<li class="nav-item"><a class="nav-link" href="<c:url value="/mypage/mysendlist" />">���� ���� ������</a></li>
		<a class="btn btn-outline-dark" onClick="window.open(this.href, '', 'width=500, height=600 scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;" 
		   href="<c:url value="/mypage/sendMessage" />">���� ������</a>
	</ul>

	<table class="table table-hover" id="mymessage" border='1' >
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

</body>
</html>