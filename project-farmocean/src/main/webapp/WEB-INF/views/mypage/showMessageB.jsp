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
				width: 100%;
			}
			th {
				max-width: 200px;
			}
			td {
				max-width: 200px;
			}
			#content {
				max-width: 200px;
				overflow: hidden;
				text-overflow:ellipsis;
			}
			
		</style>
</head>
<body>

		<h3>내가 보낸 쪽지 내용</h3> 
		<a href="<c:url value="/mypage/mysendlist" />">뒤로가기</a>

		<c:forEach items="${messageList }" var="list">
			
		<table class="table table-hover" border='1' style = "word-break: break-all">
		<thead>
			<tr>
				<th>받는 사람</th>
				<th>보낸 시간</th>
				<th>메세지 제목</th>
				<th>메세지 내용</th>
				<th>메세지 삭제</th>
			</tr>
		</thead>
				<tr>
					<td>${list.recipient_id }</td>
					<td>${list.message_date }</td>
					<td>${list.message_title }</td>
					<td id="content">${list.message_contents }</td>
					<td>
					<form action="deleteSendMessage" method="POST">
						<input type="hidden" id="message_id" name="message_id" value="${list.message_id }"/>
						<input type="submit" value="삭제하기" class="btn btn-outline-danger"/>
					</form>
					</td>
				</tr>
			</c:forEach>
			
		</table>
		

</body>
</html>