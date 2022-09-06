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
	
	<h3>Sell Member</h3>
	
	<p>${sellMember.member_id}</p>
	<p>${sellMember.member_name}</p>
	<p>${sellMember.member_nickName}</p>
	<p>${sellMember.member_point}</p>
	<p>${sellMember.member_phoneNum}</p>
	<p>${sellMember.member_accountNum}</p>
	<p>${sellMember.member_address}</p>
	<p>${sellMember.member_account_status}</p>
	<p>${sellMember.member_type}</p>
	<img src="${sellMember.member_image}" alt="" /><br />
	<button id="btn1">좋아요</button><br />
	
	<c:forEach items="${follower }" var="follow">
		
		${follow.follower_id }
		<br />
	</c:forEach>	
	
	<p></p>
	
</body>
</html>