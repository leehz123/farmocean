<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head> 
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>내가 받은 쪽지함</title>
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
			
		</style>
</head>
<body> 

		<h3>내가 받은 쪽지함</h3>

	<ul class="nav nav-tabs">
		<li class="nav-item"><a class="nav-link active"
			aria-current="page" href="<c:url value="/mypage/mylist" />">내가 받은 쪽지함</a></li>
		<li class="nav-item"><a class="nav-link" href="<c:url value="/mypage/mysendlist" />">내가 보낸 쪽지함</a></li>
		<a class="btn btn-outline-dark" onClick="window.open(this.href, '', 'width=500, height=600 scrollbars=no, resizable=no, toolbars=no, menubar=no'); return false;" 
		   href="<c:url value="/mypage/sendMessage" />">쪽지 보내기</a>
	</ul>

	<table class="table table-hover" id="mymessage" border='1' >
			<tr>
				<th>번호</th>
				<th>보낸 사람</th>
				<th>메세지 제목</th>
				<th>보낸 시간</th>
				<th>읽은 시간</th>
				<th>확인 여부</th>
			</tr>
		</table>
		
			<c:forEach items="${myID }" var="ID">
					<div id="realid" style="display:none;">${ID }</div>
			</c:forEach>	
		
		<script src="/farmocean/resources/js/mypage/mylist.js?ver=<%=System.currentTimeMillis() %>"></script>

</body>
</html>