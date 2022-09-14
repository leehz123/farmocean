<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>내가 보낸 쪽지 내용</title>
		<style>
			table {
				width: 1280px;
			}
			td {
				padding: 20px;
				border: 1px solid #666666;
			}
			th {
				padding: 20px;
				border: 1px solid #666666;
			}
		</style>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>

		<h3>내가 보낸 쪽지 내용</h3> 

		<table border='1' style = "word-break: break-all">
		
			<tr>
				<th>받는 사람</th>
				<th>보낸 시간</th>
				<th>메세지 제목</th>
				<th>메세지 내용</th>
			</tr>
			
			<c:forEach items="${messageList }" var="list">
				<tr>
					<td>${list.recipient_id }</td>
					<td>${list.message_date }</td>
					<td>${list.message_title }</td>
					<td>${list.message_contents }</td>
				</tr>
			</c:forEach>
			
		</table>
		
		<a href="<c:url value="/mypage/mysendlist" />">내가 보낸 쪽지</a>

<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>