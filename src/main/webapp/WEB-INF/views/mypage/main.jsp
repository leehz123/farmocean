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

		<h3>마이 페이지</h3>
		
		<a href="<c:url value="/mypage/mylist" />">내가 받은 쪽지</a> <br>
		<a href="<c:url value="/mypage/mysendlist" />">내가 보낸 쪽지</a> <br>
		<a onClick="window.open(this.href, '', 'width=500, height=600 scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;" 
		   href="<c:url value="/mypage/sendMessage" />">쪽지 보내기</a> <br>
		   
		   <hr>
		   
		<a href="<c:url value="/mypage/changeimg" />">회원 프로필 이미지 변경</a> <br>
		<a href="<c:url value="/mypage/changeinfo" />">회원 정보 수정</a>

<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>

</html>