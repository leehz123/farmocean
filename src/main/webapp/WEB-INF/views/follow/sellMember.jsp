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
				<img src="${sellMember.member_image}" alt="" />
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
					<button id="btn1" class="btn first">following</button>
					<div id ="div1" style="display:none">following</div>
				<%} else{%>
					<button id="btn1" class="btn third">follow</button>
					<div id="div1" style="display:none">follow</div>
				<%} %>
				<button id="btn2" class="btn third">쪽지보내기</button>
				
				<button id="btn3" class="btn">상품 보기</button>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				팔로우리스트 <br />
				<c:forEach items="${followerNickname }" var="follow">
					${follow }
					<br />
				</c:forEach>
			
			</td>
		</tr>
	</table>
		
	<p id="login_id" style="display:none">${sessionScope.loginId.member_id }</p>	
	
	<script type = "text/javascript" src="/farmocean/resources/js/follow/follow.js?"></script>
	
	
</body>
</html>