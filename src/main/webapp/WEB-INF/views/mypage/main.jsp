<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Main</title>
</head>
<body>

		<h3>���� ������</h3>
		
		<a href="<c:url value="/mypage/list" />">���� ��ü</a> <br>
		<a href="<c:url value="/mypage/mylist" />">���� ���� ����</a> <br>
		<a href="<c:url value="/mypage/mysendlist" />">���� ���� ����</a> <br>
		<a href="<c:url value="/mypage/changeimg" />">ȸ�� ������ �̹��� ����</a> <br>
		<a href="<c:url value="/mypage/changeinfo" />">ȸ�� ���� ����</a>

</body>
</html>