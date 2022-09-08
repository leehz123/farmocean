<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>내가 보낸 쪽지함</title>
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

		<h3>내가 보낸 쪽지함</h3>
		
		<table id="mymessage" border='1' style = "word-break: break-all">
		
			<tr>
				<th>받는 사람</th>
				<th>메세지 제목</th>
				<th>메세지 내용</th>
				<th>메세지 보낸 시간</th>
				<th>메세지 확인 여부</th>
			</tr>
			
		</table>
		
			<c:forEach items="${myID }" var="ID">
					<div id="realid" style="display:none;">${ID }</div>
			</c:forEach>	
		
		<script src="/farmocean/resources/js/mypage/mysendlist.js?ver=<%=System.currentTimeMillis() %>"></script>
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>