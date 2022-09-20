<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>팔로우 리스트</title>
</head>
<body>

		<h3>팔로우 리스트</h3>
		
		<% int cnt = 1; %>
		<c:forEach items="${followee}" var="follow">
			<p id="p<%=cnt%>">
				<a onClick="window.open(this.href, '', 'width=500, height=600 scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;" 
		   			href="<c:url value="/Sell/member/${follow.followee_id}" />">${follow.followee_id}</a>
		  		<button id="btn<%= cnt%>" onclick = "unfollow('${follow.followee_id}', <%= cnt%>, '${sessionScope.loginId.member_id }')">팔로우 취소하기</button>
		  	</p> 
			<% cnt++; %>
		</c:forEach>

<script type = "text/javascript" src="/farmocean/resources/js/mypage/followPage.js?"></script>
</body>
</html>