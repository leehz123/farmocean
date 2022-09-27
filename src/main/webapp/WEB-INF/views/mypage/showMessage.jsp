<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>내가 받은 쪽지 내용</title>
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


		<h3>내가 받은 쪽지 내용</h3> 
		<a href="<c:url value="/mypage/mylist" />">뒤로가기</a>
		   

			<c:forEach items="${messageList }" var="list">
			
		   
		
		<table class="table table-hover" border='1' style = "word-break: break-all">
		
			<tr>
				<th>보낸 사람</th>
				<th>보낸 시간</th>
				<th>메세지 제목</th>
				<th>메세지 내용</th>
				<th>메세지 답장</th>
				<th>메세지 삭제</th>
			</tr>
			
				<tr>
					<td>${list.sender_id }</td>
					<td>${list.message_date }</td>
					<td>${list.message_title }</td>
					<td id="content">${list.message_contents }</td>
					<td>
					<c:forEach items="${ids }" var="ids">
		   			<button type="button" class="btn btn-outline-primary" onClick="fnWinOpen(500, 600, '<c:url value="/mypage/sendMessages?id=${ids }" />'); return false;">
		   			답장하기
		   			</button>
		   			
		   			
		   
		   </c:forEach>
					</td>
					<td>
					<form action="deleteMessage" method="POST">
						<input type="hidden" id="message_id" name="message_id" value="${list.message_id }"/>
						<input type="submit" value="삭제하기" class="btn btn-outline-danger" />
					</form>
					</td>
				</tr>
			</c:forEach>
			
		</table>
	

</body>
</html>