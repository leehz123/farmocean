<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	
	<% 
		int cnt = 0; 
	%>
	<c:forEach items="${follower }" var="follow">
		<c:if test="${follow.follower_id eq 'kings'}"> // 세션로그인으로 바꾸기
			<% cnt++; %>
		</c:if>
	</c:forEach>
	
	<h3>Sell Member</h3>
	
	<div id="sellMember_id">${sellMember.member_id}</div>
	<p>${sellMember.member_name}</p>
	<p>${sellMember.member_nickName}</p>
	<p>${sellMember.member_point}</p>
	<p>${sellMember.member_phoneNum}</p>
	<p>${sellMember.member_accountNum}</p>
	<p>${sellMember.member_address}</p>
	<p>${sellMember.member_account_status}</p>
	<p>${sellMember.member_type}</p>
	<img src="${sellMember.member_image}" alt="" /><br />
	
	<%if(cnt == 1){ %>
		<button id="btn1">팔로우중</button><br />
		<div id ="div1">following</div>
	<%} else{%>
		<button id="btn1">팔로우하기</button><br />
		<div id="div1">follow</div>
	<%} %>
	
	<c:forEach items="${follower }" var="follow">
		${follow.follower_id }
		<br />
	</c:forEach>	
	<script src="/farmocean/resources/js/follow/follow.js"></script>
	
	
</body>
</html>