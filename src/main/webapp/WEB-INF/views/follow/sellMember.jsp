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
	<p>이름 : ${sellMember.member_name}</p>
	<p>닉네임 : ${sellMember.member_nickName}</p>
	<p>포인트 : ${sellMember.member_point}</p>
	<p>핸드폰번호 : ${sellMember.member_phoneNum}</p>
	<p>고객번호 : ${sellMember.member_accountNum}</p>
	<p>주소 : ${sellMember.member_address}</p>
	<p>고객정보 : ${sellMember.member_account_status}</p>
	<p>유형 : ${sellMember.member_type}</p>
	<img src="${sellMember.member_image}" alt="" /><br />
	
	<%if(cnt == 1){ %>
		<button id="btn1">following</button><br />
		<div id ="div1">following</div>
	<%} else{%>
		<button id="btn1">follow</button><br />
		<div id="div1">follow</div>
	<%} %>
	<button id="btn2">쪽지보내기</button><br />
	<button id="btn3">판매자 상품 보기</button><br />
	<p>팔로우중인 회원들</p>
	<c:forEach items="${follower }" var="follow">
		${follow.follower_id }
		<br />
	</c:forEach>	
	<script src="/farmocean/resources/js/follow/follow.js"></script>
	
	
</body>
</html>