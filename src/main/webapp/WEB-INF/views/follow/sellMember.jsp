<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/follow/follow.css" />" />
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	
	<% 
		int cnt = 0; 
	%>
	<c:forEach items="${follower }" var="follow">
		<c:if test="${follow.follower_id eq sessionScope.loginId.member_id}"> 
			<% cnt++; %>
		</c:if>
	</c:forEach>
		
	<table border="2" bordercolor="skyblue" width="450" height="600">
		<tr>
			<td rowspan="8" width="70%">
				<img src="/farmocean/resources/image/mypage/${sellMember.member_image}" alt=""  width="300" height="300"/>
			</td>
			<td>아이디</td>
		</tr>
		<tr>
			<td id="sellMember_id">${sellMember.member_id}</td>
		</tr>
		<tr>
			<td>이름</td>
		</tr>
		<tr>
			<td>${name}</td>
		</tr>
		<tr>
			<td>닉네임</td>
		</tr>
		<tr>
			<td>${sellMember.member_nickName}</td>
		</tr>
		<tr>
			<td>유형</td>
		</tr>
		<tr>
			<td>
				${sellMember.member_type eq "S" ? "판매자" : "구매자"}
			</td>
		</tr>
		<tr>
			<td colspan="2">고객번호 : ${accountNum}</td>
		</tr>
		<tr>
			<td colspan="2">핸드폰번호 : ${phoneNum}</td>
		</tr>
		<tr>
			<td colspan="2">주소 : ${address}</td>
		</tr>
		<tr>
			<td colspan="2">
				<%if(cnt == 1){ %>
					<button id="btn1" class="btn first">언팔로우</button>
					<div id ="div1" style="display:none">언팔로우</div>
				<%} else{%>
					<button id="btn1" class="btn first">팔로우</button>
					<div id="div1" style="display:none">팔로우</div>
				<%} %>
				<button id="btn2" class="btn first">쪽지</button>
				
				<button id="btn3" class="btn first">판매상품</button>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<h4>팔로워 리스트</h4><br />
				<c:forEach items="${followerNickname }" var="follow">
					- ${follow }
					<br />
				</c:forEach>
			
			</td>
		</tr>
	</table>
		
	<p id="login_id" style="display:none">${sessionScope.loginId.member_id }</p>	
	
	<script type = "text/javascript" src="/farmocean/resources/js/follow/follow.js?"></script>
	
	
</body>
</html>