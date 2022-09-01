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

		<h3>마이 페이지</h3>
		
		<a href="<c:url value="/mypage/list" />">쪽지 전체</a> <br>
		<a href="<c:url value="/mypage/mylist" />">내가 받은 쪽지</a> <br>
		<a href="<c:url value="/mypage/mysendlist" />">내가 보낸 쪽지</a> <br>
		<a href="<c:url value="/mypage/changeimg" />">회원 프로필 이미지 변경</a> <br>
		<a href="<c:url value="/mypage/changeinfo" />">회원 정보 수정</a>

</body>
</html>