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
		<c:if test="${follow.follower_id eq 'kings'}"> // ���Ƿα������� �ٲٱ�
			<% cnt++; %>
		</c:if>
	</c:forEach>
	
	<h3>Sell Member</h3>
	
	<div id="sellMember_id">${sellMember.member_id}</div>
	<p>�̸� : ${sellMember.member_name}</p>
	<p>�г��� : ${sellMember.member_nickName}</p>
	<p>����Ʈ : ${sellMember.member_point}</p>
	<p>�ڵ�����ȣ : ${sellMember.member_phoneNum}</p>
	<p>����ȣ : ${sellMember.member_accountNum}</p>
	<p>�ּ� : ${sellMember.member_address}</p>
	<p>������ : ${sellMember.member_account_status}</p>
	<p>���� : ${sellMember.member_type}</p>
	<img src="${sellMember.member_image}" alt="" /><br />
	
	<%if(cnt == 1){ %>
		<button id="btn1">following</button><br />
		<div id ="div1">following</div>
	<%} else{%>
		<button id="btn1">follow</button><br />
		<div id="div1">follow</div>
	<%} %>
	<button id="btn2">����������</button><br />
	<button id="btn3">�Ǹ��� ��ǰ ����</button><br />
	<p>�ȷο����� ȸ����</p>
	<c:forEach items="${follower }" var="follow">
		${follow.follower_id }
		<br />
	</c:forEach>	
	<script src="/farmocean/resources/js/follow/follow.js"></script>
	
	
</body>
</html>