<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>내가 받은 쪽지함</title>
</head>
<body>

		<h3>내가 받은 쪽지함</h3> 

		<table border='1'>
		
			<tr>
				<th>메세지 번호</th>
				<th>발신자</th>
				<th>수신자</th>
				<th>메세지 제목</th>
				<th>메세지 내용</th>
				<th>메세지 보낸 시간</th>
				<th>메세지 확인 여부</th>
			</tr>
			
			<c:forEach items="${myList }" var="list">
				<tr>
					<td>${list.message_id }</td>
					<td>${list.sender_id }</td>
					<td>${list.recipient_id }</td>
					<td>${list.message_title }</td>
					<td>${list.message_contents }</td>
					<td>${list.message_date }</td>
					<td>${list.message_check }</td>
				</tr>
			</c:forEach>
			
		</table>
		
		<a href="<c:url value="/mypage/main" />">main으로 가기</a>
</body>
</html>