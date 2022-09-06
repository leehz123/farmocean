<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>내가 받은 쪽지함</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>

		<h3>내가 받은 쪽지함</h3> 

		<table border='1'>
		
			<tr>
				<th>메세지 번호</th>
				<th>보낸 사람</th>
				<th>메세지 제목</th>
				<th>메세지 내용</th>
				<th>메세지 보낸 시간</th>
				<th>메세지 확인 여부</th>
			</tr>
			
			<c:forEach items="${myID }" var="ID">
					<div id="myid" style="display:none;">${ID }</div>
			</c:forEach>
				<tr>
					<!-- 
					<td>${list.message_id }</td>
					<td>${list.sender_id }</td>
					<td>${list.message_title }</td>
					<td>${list.message_contents }</td>
					<td>${list.message_date }</td>
					<td>${list.message_check }</td>
					 -->
				</tr>
			
		</table>
		
		<script src="/farmocean/resources/js/mypage/mylist.js?ver=<%=System.currentTimeMillis() %>"></script>
</body>
</html>