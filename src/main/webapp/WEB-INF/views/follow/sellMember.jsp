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
		<c:if test="${follow.follower_id eq sessionScope.loginId.member_id}"> 
			<% cnt++; %>
		</c:if>
	</c:forEach>
		
	<table border="2" bordercolor="skyblue" width="450" height="600">
		<tr>
			<td rowspan="5" width="70%">
				<img src="${sellMember.member_image}" alt="" />
			</td>
			<td>���̵�:${sellMember.member_id}</td>
		</tr>
		<tr>
			<td>�̸�:${sellMember.member_name}</td>
		</tr>
		<tr>
			<td>�г���:${sellMember.member_nickName}</td>
		</tr>
		<tr>
			<td>����ȣ"${sellMember.member_accountNum}</td>
		</tr>
		<tr>
			<td>���� : ${sellMember.member_type}:</td>
		</tr>
		<tr>
			<td colspan="2">�ڵ�����ȣ : ${sellMember.member_phoneNum}</td>
		</tr>
		<tr>
			<td colspan="2">�ּ� : ${sellMember.member_address}</td>
		</tr>
		<tr>
			<td colspan="2">
				<%if(cnt == 1){ %>
					<button id="btn1">following</button>
					<div id ="div1" style="display:none">following</div>
				<%} else{%>
					<button id="btn1">follow</button>
					<div id="div1" style="display:none">follow</div>
				<%} %>
				<button id="btn2" >����������</button>
				
				<button id="btn3">�Ǹ��� ��ǰ ����</button>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				�ȷο츮��Ʈ <br />
				<c:forEach items="${follower }" var="follow">
					${follow.follower_id }
					<br />
				</c:forEach>
			</td>
		</tr>
	</table>
		
	<p id="login_id">${sessionScope.loginId.member_id }</p>	
	
	<script type = "text/javascript" src="/farmocean/resources/js/follow/follow.js?"></script>
	
	
</body>
</html>