<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>Main</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>

		<h3>���� ������</h3>
		
		<a href="<c:url value="/mypage/mylist" />">���� ���� ����</a> <br>
		<a href="<c:url value="/mypage/mysendlist" />">���� ���� ����</a> <br>
		<a onClick="window.open(this.href, '', 'width=500, height=600 scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;" 
		   href="<c:url value="/mypage/sendMessage" />">���� ������</a> <br>
		   
		   <hr>
		   
		<a href="<c:url value="/mypage/changeimg" />">ȸ�� ������ �̹��� ����</a> <br>
		<a href="<c:url value="/mypage/changeinfo" />">ȸ�� ���� ����</a>

<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>

</html>